package com.raebagi.order.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raebagi.order.entity.Menu;
import com.raebagi.order.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuApiController {

	private final MenuRepository menuRepository;

	@GetMapping
	public List<Menu> getMenus(){
		return menuRepository.findAllByOrderByCategoryAsc();
	}
}
