package com.raebagi.order.dto;

import java.util.List;

public class OrderRequestDto {
	private int roomNumber; // 방 번호 (OrderService에서 사용)
	private List<OrderItemDto> items; // 주문 메뉴 목록

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public List<OrderItemDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}
}