package com.raebagi.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private int price;
	private String category;
	private String description;

	@Column(name = "sold_out", nullable = false)
	private boolean soldOut = false; // 기본값은 판매 중(false)

	public Menu(String name, int price, String category, String description) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.description = description;
		this.soldOut = false;
	}

	// 품절 상태 변경을 위한 Setter 추가
	public void setSoldOut(boolean soldOut) {
		this.soldOut = soldOut;
	}
}