package com.raebagi.order.controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.raebagi.order.dto.*;
import com.raebagi.order.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;

	@PostMapping("/orders")
	public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto request) {
		orderService.createOrder(request);
		return ResponseEntity.ok("주문이 접수되었습니다!");
	}

	@GetMapping("/orders/room/{roomNumber}")
	public ResponseEntity<List<OrderResponseDto>> getOrdersByRoom(
		@PathVariable("roomNumber") int roomNumber) {
		return ResponseEntity.ok(orderService.getOrdersByRoom(roomNumber));
	}
}
