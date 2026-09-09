package com.raebagi.order.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders") // 'order'는 예약어인 경우가 많아 명시 권장
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
	private OrderStatus status; // PENDING, COMPLETED

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	// 연관관계 편의 메서드
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	// 주문 생성 메서드
	public static Order createOrder(int roomNumber, OrderItem... orderItems) {
		Order order = new Order();
		order.roomNumber = roomNumber;
		for (OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);
		}
		order.status = OrderStatus.PENDING;
		order.orderTime = LocalDateTime.now();
		return order;
	}
}