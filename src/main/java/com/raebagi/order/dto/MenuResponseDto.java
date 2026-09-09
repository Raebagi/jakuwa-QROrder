package com.raebagi.order.dto;
import com.raebagi.order.entity.Menu;
import lombok.Getter;

@Getter
public class MenuResponseDto {
	private final Long id;
	private final String name;
	private final int price;
	private final String category;
	private final String description;
	private final boolean soldOut;
	public MenuResponseDto(Menu menu) {
		id = menu.getId();
		name = menu.getName();
		price = menu.getPrice();
		category = menu.getCategory();
		description = menu.getDescription();
		soldOut = menu.isSoldOut();
	}
}
