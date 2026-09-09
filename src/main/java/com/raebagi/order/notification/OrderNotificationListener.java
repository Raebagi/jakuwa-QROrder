package com.raebagi.order.notification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderNotificationListener {
	private final OrderNotifier notifier;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onOrderCreated(OrderNotification notification) {
		try {
			notifier.notifyOrderCreated(notification);
		} catch (Exception e) {
			// DB에는 이미 주문이 저장됨. 주문 실패 응답으로 재주문을 유도하지 않습니다.
			// 웹훅 토큰이 예외 메시지에 포함될 수 있으므로 메시지/스택을 출력하지 않습니다.
			log.error("주문 알림 실패. orderId={}, error={}",
				notification.getOrderId(), e.getClass().getSimpleName());
		}
	}
}
