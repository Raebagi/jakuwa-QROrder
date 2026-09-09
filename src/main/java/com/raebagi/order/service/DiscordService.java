package com.raebagi.order.service;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.raebagi.order.notification.*;

@Service
public class DiscordService implements OrderNotifier {
	private final RestTemplate restTemplate;
	private final String webhookUrl;

	public DiscordService(@Qualifier("discordRestTemplate") RestTemplate restTemplate,
		@Value("${app.discord.webhook-url}") String webhookUrl) {
		if (webhookUrl == null || webhookUrl.isBlank()) {
			throw new IllegalArgumentException("디스코드 웹훅 설정이 필요합니다.");
		}
		this.restTemplate = restTemplate;
		this.webhookUrl = webhookUrl;
	}

	@Override
	public void notifyOrderCreated(OrderNotification notification) {
		String header = "[주문 " + notification.getOrderId() + " / "
			+ notification.getRoomNumber() + "번 방]\n";
		String content = header + String.join("\n", notification.getItemLines());
		// 주문이 길어도 메뉴를 누락하지 않고 여러 메시지로 전송합니다.
		for (int start = 0; start < content.length();) {
			int end = Math.min(start + 1800, content.length());
			if (end < content.length() && Character.isHighSurrogate(content.charAt(end - 1))) {
				end--;
			}
			Map<String, Object> payload = Map.of(
				"content", content.substring(start, end),
				"allowed_mentions", Map.of("parse", List.of()));
			restTemplate.postForObject(webhookUrl, payload, String.class);
			start = end;
		}
	}
}
