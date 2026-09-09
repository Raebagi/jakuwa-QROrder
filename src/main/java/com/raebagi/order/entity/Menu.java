package com.raebagi.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int price;
	private String category;
	private String description;
	@Column(name = "sold_out", nullable = false)
	private boolean soldOut = false;

	public Menu(String name, int price, String category, String description) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("메뉴 이름은 필수입니다.");
		}
		if (price < 0) {
			throw new IllegalArgumentException("가격은 0원 이상이어야 합니다.");
		}
		if (category == null || category.isBlank()) {
			throw new IllegalArgumentException("카테고리는 필수입니다.");
		}
		this.name = name;
		this.price = price;
		this.category = category;
		this.description = description;
	}

	public void markSoldOut() {
		this.soldOut = true;
	}

	public void resumeSale() {
		this.soldOut = false;
	}

	public void ensureOrderable() {
		if (soldOut) {
			throw new com.raebagi.order.exception.OrderConflictException(
				name + "은(는) 품절된 메뉴입니다.");
		}
	}
}
