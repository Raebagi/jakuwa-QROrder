package com.raebagi.order.dto;

import com.raebagi.order.entity.Order;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponseDto {
	private Long id;
	private int roomNumber;
	private String status;
	private LocalDateTime orderTime;
	private List<OrderItemResponseDto> items;

	public OrderResponseDto(Order order) {
		this.id = order.getId();
		this.roomNumber = order.getRoomNumber();
		this.status = order.getStatus().name();
		this.orderTime = order.getOrderTime();
		this.items = order.getOrderItems().stream()
			.map(OrderItemResponseDto::new)
			.collect(Collectors.toList());
	}

	@Getter
	public static class OrderItemResponseDto {
		private String menuName;
		private int orderPrice;
		private int count;

		public OrderItemResponseDto(com.raebagi.order.entity.OrderItem orderItem) {
			this.menuName = orderItem.getMenu().getName();
			this.orderPrice = orderItem.getOrderPrice();
			this.count = orderItem.getCount();
		}
	}
}