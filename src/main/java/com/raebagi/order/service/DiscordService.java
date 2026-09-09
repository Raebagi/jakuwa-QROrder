package com.raebagi.order.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.raebagi.order.entity.Order;

@Service
public class DiscordService {

	// 중복된 부분을 제거하고 올바른 URL 하나만 남깁니다.
	private static final String DISCORD_WEBHOOK_URL = "https://discord.com/api/webhooks/1536662475284418621/hK_YEoFxMJizusqtsVlvwJHOpts4gM_VveO0WIjOTR0tgkwz3pSdVOxNIuiFuM3AiiIa";

	public void sendOrderNotification(Order order) {
		RestTemplate restTemplate = new RestTemplate();

		// 메뉴 목록 조합
		StringBuilder itemsText = new StringBuilder();
		order.getOrderItems().forEach(orderItem -> {
			itemsText.append("• ")
				.append(orderItem.getMenu().getName())
				.append(" ")
				.append(orderItem.getCount())
				.append("개\n");
		});

		// 방 번호와 메뉴만 나오는 포맷
		String content = "**[ " + order.getRoomNumber() + "번 방 ]**\n" + itemsText.toString();

		Map<String, String> payload = new HashMap<>();
		payload.put("content", content);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, String>> entity = new HttpEntity<>(payload, headers);

		try {
			restTemplate.postForObject(DISCORD_WEBHOOK_URL, entity, String.class);
			System.out.println("디스코드 알림 전송 성공!"); // 성공 시 찍힘
		} catch (Exception e) {
			System.err.println("디스코드 알림 전송 실패 원인: " + e.getMessage());
			e.printStackTrace(); // 상세 에러 스택 트레이스 출력
		}
	}
}