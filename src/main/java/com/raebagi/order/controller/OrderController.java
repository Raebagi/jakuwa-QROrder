package com.raebagi.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raebagi.order.dto.OrderRequestDto;
import com.raebagi.order.dto.OrderResponseDto;
import com.raebagi.order.service.DiscordService;
import com.raebagi.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api") // 기존 /api/orders에서 /api로 변경하여 관리자 경로와 주문 경로를 모두 아우르도록 합니다.
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final DiscordService discordService;

	@PostMapping("/orders")
	public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto requestDto) {
		try {
			orderService.createOrder(requestDto); // 서비스에서 주문 생성 및 디스코드 알림까지 모두 처리
			return ResponseEntity.ok("주문이 접수되었습니다!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("주문 실패! 벨을 눌러 직원을 호출해주세요.");
		}
	}

	@GetMapping("/orders/room/{roomNumber}")
	public ResponseEntity<List<OrderResponseDto>> getOrdersByRoom(@PathVariable int roomNumber) {
		List<OrderResponseDto> responseDtos = orderService.getOrdersByRoom(roomNumber);
		return ResponseEntity.ok(responseDtos);
	}

	// [추가] 사장님 품절 상태 변경 API (/api/admin/menus/{id}/sold-out)
	@PatchMapping("/admin/menus/{id}/sold-out")
	public ResponseEntity<String> toggleSoldOut(@PathVariable Long id) {
		String menuName = orderService.toggleSoldOut(id);
		return ResponseEntity.ok(menuName + " 품절 상태 변경 완료");
	}
}