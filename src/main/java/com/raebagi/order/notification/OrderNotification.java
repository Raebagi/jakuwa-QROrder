package com.raebagi.order.notification;
import java.util.List;
import java.util.stream.Collectors;
import com.raebagi.order.entity.Order;
import lombok.Getter;

@Getter
public final class OrderNotification {
	private final Long orderId;
	private final int roomNumber;
	private final List<String> itemLines;

	private OrderNotification(Long orderId, int roomNumber, List<String> lines) {
		this.orderId = orderId;
		this.roomNumber = roomNumber;
		this.itemLines = List.copyOf(lines);
	}
	public static OrderNotification from(Order order) {
		List<String> lines = order.getOrderItems().stream().map(item ->
				item.getOrderedMenuName() + " " + item.getCount() + "개"
					+ (item.getBaseOption() == null ? "" : " (" + item.getBaseOption() + ")"))
			.collect(Collectors.toList());
		return new OrderNotification(order.getId(), order.getRoomNumber(), lines);
	}
}
