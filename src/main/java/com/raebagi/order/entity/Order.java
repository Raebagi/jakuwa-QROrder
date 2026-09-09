package com.raebagi.order.entity;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;
	private int roomNumber;
	private LocalDateTime orderTime;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	public List<OrderItem> getOrderItems() {
		return Collections.unmodifiableList(orderItems);
	}

	public static Order createOrder(int roomNumber, OrderItem... items) {
		if (roomNumber <= 0 || items == null || items.length == 0) {
			throw new IllegalArgumentException("유효한 방 번호와 주문 항목이 필요합니다.");
		}
		// 일부 항목만 연결된 채 예외가 나지 않도록 전체를 먼저 검증합니다.
		Set<OrderItem> unique = Collections.newSetFromMap(new IdentityHashMap<>());
		for (OrderItem item : items) {
			if (item == null || item.getOrder() != null || !unique.add(item)) {
				throw new IllegalArgumentException("중복되거나 이미 주문에 연결된 항목입니다.");
			}
		}
		Order order = new Order();
		order.roomNumber = roomNumber;
		order.status = OrderStatus.PENDING;
		order.orderTime = LocalDateTime.now();
		for (OrderItem item : items) {
			item.attachTo(order);
			order.orderItems.add(item);
		}
		return order;
	}

	public void complete() {
		if (status != OrderStatus.PENDING) {
			throw new com.raebagi.order.exception.OrderConflictException(
				"접수 상태의 주문만 완료할 수 있습니다.");
		}
		status = OrderStatus.COMPLETED;
	}
}
