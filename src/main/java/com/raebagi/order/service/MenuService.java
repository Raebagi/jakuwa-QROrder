package com.raebagi.order.service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.raebagi.order.dto.MenuResponseDto;
import com.raebagi.order.entity.Menu;
import com.raebagi.order.repository.MenuRepository;
import com.raebagi.order.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {
	private final MenuRepository menuRepository;

	public List<MenuResponseDto> getMenus() {
		return menuRepository.findAllByOrderByCategoryAsc().stream()
			.map(MenuResponseDto::new).collect(Collectors.toList());
	}

	@Transactional
	public String changeAvailability(Long id, Boolean soldOut) {
		if (id == null || id <= 0 || soldOut == null) {
			throw new IllegalArgumentException("메뉴 ID와 soldOut 값이 필요합니다.");
		}
		Menu menu = menuRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("메뉴를 찾을 수 없습니다."));
		if (soldOut) {
			menu.markSoldOut();
		} else {
			menu.resumeSale();
		}
		return menu.getName();
	}

	@Transactional
	public String toggleSoldOut(Long id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException(
				"올바른 메뉴 ID가 필요합니다."
			);
		}

		Menu menu = menuRepository.findById(id)
			.orElseThrow(() ->
				new ResourceNotFoundException(
					"메뉴를 찾을 수 없습니다. id=" + id
				)
			);

		menu.toggleSoldOut();

		return menu.getName();
	}
}
