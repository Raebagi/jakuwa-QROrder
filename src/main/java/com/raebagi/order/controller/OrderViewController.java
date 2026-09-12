package com.raebagi.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderViewController {

	@GetMapping("/order")
	public String orderPage(
		@RequestParam("room") int roomNumber,
		Model model) {

		if (roomNumber < 0) {
			throw new IllegalArgumentException("방 번호는 0 이상이어야 합니다.");
		}

		// 기존 관리자 접속 주소 유지
		if (roomNumber == 0) {
			return "redirect:/admin";
		}

		model.addAttribute("roomNumber", roomNumber);
		model.addAttribute("adminMode", false);

		return "order";
	}

	@GetMapping("/admin")
	public String adminPage(Model model) {
		// SecurityConfig에서 관리자 권한을 확인한 후 실행
		model.addAttribute("roomNumber", 0);
		model.addAttribute("adminMode", true);

		return "order";
	}

	@GetMapping("/order/history")
	public String orderHistoryPage(
		@RequestParam("room") int roomNumber,
		Model model) {

		if (roomNumber < 0) {
			throw new IllegalArgumentException("방 번호는 0 이상이어야 합니다.");
		}

		// 0번 주문 내역도 관리자 인증을 거치도록 연결
		if (roomNumber == 0) {
			return "redirect:/admin/history";
		}

		model.addAttribute("roomNumber", roomNumber);
		model.addAttribute("adminMode", false);

		return "history";
	}

	@GetMapping("/admin/history")
	public String adminHistoryPage(Model model) {
		model.addAttribute("roomNumber", 0);
		model.addAttribute("adminMode", true);

		return "history";
	}
}