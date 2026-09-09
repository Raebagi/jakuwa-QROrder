package com.raebagi.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raebagi.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	// 태블릿 대시보드에서 최신 주문순으로 정렬해서 보여주기 위한 메서드
	List<Order> findByRoomNumber(int roomNumber);

	List<Order> findAllByOrderByOrderTimeDesc();
}