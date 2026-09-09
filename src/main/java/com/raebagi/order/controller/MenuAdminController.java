package com.raebagi.order.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.raebagi.order.dto.MenuAvailabilityRequest;
import com.raebagi.order.service.MenuService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/menus")
@RequiredArgsConstructor
public class MenuAdminController {
	private final MenuService menuService;

	@PatchMapping("/{id}/sold-out")
	public ResponseEntity<String> changeAvailability(
		@PathVariable("id") Long id, @RequestBody MenuAvailabilityRequest request) {
		String name = menuService.changeAvailability(id, request.getSoldOut());
		return ResponseEntity.ok(name + " 품절 상태 변경 완료");
	}
}
