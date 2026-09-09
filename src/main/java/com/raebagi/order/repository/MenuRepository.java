package com.raebagi.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raebagi.order.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	// 카테고리별로 묶어서 가져오기 위해 정렬 조건을 추가했습니다.
	List<Menu> findAllByOrderByCategoryAsc();
}
