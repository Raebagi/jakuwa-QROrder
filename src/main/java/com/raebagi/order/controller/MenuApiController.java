package com.raebagi.order.controller;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.raebagi.order.dto.MenuResponseDto;
import com.raebagi.order.service.MenuService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuApiController {
	private final MenuService menuService;
	@GetMapping
	public List<MenuResponseDto> getMenus() { return menuService.getMenus(); }
}
