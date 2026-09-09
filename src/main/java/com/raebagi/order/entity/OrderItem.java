package com.raebagi.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_item_id")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;
	private int orderPrice;
	private int count;

	// 기존 데이터의 호환성을 위해 nullable. 적용 전 README의 DB 변경 참고.
	@Column(name = "menu_name_snapshot", length = 255)
	private String menuNameSnapshot;
	@Column(name = "base_option", length = 200)
	private String baseOption;

	public static OrderItem createOrderItem(Menu menu, int count, String baseOption) {
		if (menu == null) {
			throw new IllegalArgumentException("메뉴는 필수입니다.");
		}
		if (count <= 0) {
			throw new IllegalArgumentException("수량은 1 이상이어야 합니다.");
		}
		if (baseOption != null && baseOption.length() > 200) {
			throw new IllegalArgumentException("옵션은 200자 이하여야 합니다.");
		}
		menu.ensureOrderable();
		OrderItem item = new OrderItem();
		item.menu = menu;
		item.menuNameSnapshot = menu.getName();
		item.orderPrice = menu.getPrice();
		item.count = count;
		item.baseOption = baseOption == null || baseOption.isBlank()
			? null : baseOption.trim();
		return item;
	}

	public String getOrderedMenuName() {
		return menuNameSnapshot != null ? menuNameSnapshot : menu.getName();
	}

	void attachTo(Order order) {
		if (order == null || this.order != null) {
			throw new IllegalStateException("주문 항목은 한 주문에 한 번만 연결할 수 있습니다.");
		}
		this.order = order;
	}
}
