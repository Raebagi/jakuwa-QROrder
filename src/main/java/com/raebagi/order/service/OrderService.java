package com.raebagi.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raebagi.order.dto.OrderItemDto;
import com.raebagi.order.dto.OrderRequestDto;
import com.raebagi.order.dto.OrderResponseDto;
import com.raebagi.order.entity.Menu;
import com.raebagi.order.entity.Order;
import com.raebagi.order.entity.OrderItem;
import com.raebagi.order.repository.MenuRepository;
import com.raebagi.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final MenuRepository menuRepository;
	private final DiscordService discordService; // 디스코드 알림 서비스 주입

	public Order createOrder(OrderRequestDto requestDto) {
		List<OrderItem> orderItems = new ArrayList<>();

		for (OrderItemDto itemDto : requestDto.getItems()) {
			Menu menu = menuRepository.findById(itemDto.getMenuId())
				.orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));

			// 품절된 메뉴인지 검사하는 방어 로직
			if (menu.isSoldOut()) {
				throw new IllegalStateException(menu.getName() + "은(는) 품절된 메뉴입니다.");
			}

			OrderItem orderItem = OrderItem.createOrderItem(menu, menu.getPrice(), itemDto.getCount());
			orderItems.add(orderItem);
		}

		Order order = Order.createOrder(requestDto.getRoomNumber(), orderItems.toArray(new OrderItem[0]));
		orderRepository.save(order);

		// 알림은 여기서 한 번만 호출!
		discordService.sendOrderNotification(order);
		return order;
	}

	@Transactional(readOnly = true) // 조회 전용이므로 성능 최적화
	public List<OrderResponseDto> getOrdersByRoom(int roomNumber) {
		List<Order> orders = orderRepository.findByRoomNumber(roomNumber);
		return orders.stream()
			.map(OrderResponseDto::new)
			.collect(Collectors.toList());
	}

	// [추가] 컨트롤러에서 호출할 수 있는 품절 상태 토글 메서드
	public String toggleSoldOut(Long id) {
		Menu menu = menuRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다. id=" + id));

		menu.setSoldOut(!menu.isSoldOut());
		return menu.getName();
	}
}