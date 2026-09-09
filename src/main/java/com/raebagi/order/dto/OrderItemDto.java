package com.raebagi.order.dto;

public class OrderItemDto {
	private Long menuId;
	private String name;
	private int count;
	private String baseOption;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getBaseOption() {
		return baseOption;
	}
	public void setBaseOption(String baseOption) {
		this.baseOption = baseOption;
	}
}