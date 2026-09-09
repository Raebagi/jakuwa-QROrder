package com.raebagi.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller

public class OrderViewController {

	@GetMapping("/order")
	public String orderPage(@RequestParam("room")int roomNumber, Model model) {
		if (roomNumber <= 0) {
			throw new IllegalArgumentException("방 번호는 양수여야 합니다.");
		}
		model.addAttribute("roomNumber", roomNumber);
		return "order";
	}

	@GetMapping("/order/history")
	public String orderHistoryPage(@RequestParam("room") int roomNumber, Model model) {
		if (roomNumber <= 0) {
			throw new IllegalArgumentException("방 번호는 양수여야 합니다.");
		}
		model.addAttribute("roomNumber", roomNumber);
		return "history"; // templates/history.html
	}
}
