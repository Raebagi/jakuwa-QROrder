package com.raebagi.order.dto;
import lombok.Getter;
import lombok.Setter;

// 요청 DTO는 JSON 역직렬화를 위해 setter를 허용합니다.
@Getter
@Setter
public class MenuAvailabilityRequest {
	private Boolean soldOut;
}
