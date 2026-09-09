package com.raebagi.order.service;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.raebagi.order.dto.*;
import com.raebagi.order.entity.*;
import com.raebagi.order.repository.*;
import com.raebagi.order.exception.ResourceNotFoundException;
import com.raebagi.order.notification.OrderNotification;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
	private final OrderRepository orderRepository;
	private final MenuRepository menuRepository;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public Order createOrder(OrderRequestDto request) {
		validate(request);
		Set<Long> ids = request.getItems().stream()
			.map(OrderItemDto::getMenuId).collect(Collectors.toSet());
		Map<Long, Menu> menus = menuRepository.findAllById(ids).stream()
			.collect(Collectors.toMap(Menu::getId, menu -> menu));
		List<OrderItem> items = new ArrayList<>();
		for (OrderItemDto input : request.getItems()) {
			Menu menu = menus.get(input.getMenuId());
			if (menu == null) {
				throw new ResourceNotFoundException("메뉴를 찾을 수 없습니다.");
			}
			items.add(OrderItem.createOrderItem(menu, input.getCount(), input.getBaseOption()));
		}
		Order order = Order.createOrder(request.getRoomNumber(), items.toArray(new OrderItem[0]));
		orderRepository.save(order);
		// 지연 로딩 엔티티가 아닌 불변 데이터만 이벤트에 전달합니다.
		eventPublisher.publishEvent(OrderNotification.from(order));
		return order;
	}

	public List<OrderResponseDto> getOrdersByRoom(int roomNumber) {
		if (roomNumber <= 0) {
			throw new IllegalArgumentException("방 번호는 양수여야 합니다.");
		}
		return orderRepository.findByRoomNumberOrderByOrderTimeDescIdDesc(roomNumber)
			.stream().map(OrderResponseDto::new).collect(Collectors.toList());
	}

	private void validate(OrderRequestDto request) {
		if (request == null || request.getRoomNumber() <= 0) {
			throw new IllegalArgumentException("유효한 방 번호가 필요합니다.");
		}
		if (request.getItems() == null || request.getItems().isEmpty()) {
			throw new IllegalArgumentException("주문 메뉴가 필요합니다.");
		}
		for (OrderItemDto item : request.getItems()) {
			if (item == null || item.getMenuId() == null || item.getMenuId() <= 0
				|| item.getCount() <= 0) {
				throw new IllegalArgumentException("메뉴 ID와 양수 수량이 필요합니다.");
			}
			if (item.getBaseOption() != null && item.getBaseOption().length() > 200) {
				throw new IllegalArgumentException("옵션은 200자 이하여야 합니다.");
			}
		}
	}
}
