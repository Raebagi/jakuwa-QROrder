package com.raebagi.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raebagi.order.service.MenuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/menus")
@RequiredArgsConstructor
public class MenuAdminController {

	private final MenuService menuService;

	@PatchMapping("/{id}/sold-out")
	public ResponseEntity<String> toggleSoldOut(
		@PathVariable("id") Long id
	) {
		String menuName = menuService.toggleSoldOut(id);

		return ResponseEntity.ok(
			menuName + " 품절 상태 변경 완료"
		);
	}
}