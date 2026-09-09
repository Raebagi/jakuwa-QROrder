package com.raebagi.order.repository;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.raebagi.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	@EntityGraph(attributePaths = {"orderItems", "orderItems.menu"})
	List<Order> findByRoomNumberOrderByOrderTimeDescIdDesc(int roomNumber);

	@EntityGraph(attributePaths = {"orderItems", "orderItems.menu"})
	List<Order> findAllByOrderByOrderTimeDesc();
}
