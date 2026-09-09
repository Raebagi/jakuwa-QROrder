package com.raebagi.order.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.raebagi.order.exception.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> invalid(IllegalArgumentException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	@ExceptionHandler({HttpMessageNotReadableException.class,
		MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
	public ResponseEntity<String> malformed(Exception e) {
		return ResponseEntity.badRequest().body("요청 형식을 확인해주세요.");
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> missing(ResourceNotFoundException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}
	@ExceptionHandler(OrderConflictException.class)
	public ResponseEntity<String> conflict(OrderConflictException e) {
		return ResponseEntity.status(409).body(e.getMessage());
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> unexpected(Exception e) {
		log.error("요청 처리 실패: {}", e.getClass().getSimpleName());
		return ResponseEntity.internalServerError().body("처리 중 오류가 발생했습니다. 직원에게 문의해주세요.");
	}
}
