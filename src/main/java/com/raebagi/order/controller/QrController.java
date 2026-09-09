package com.raebagi.order.controller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.raebagi.order.service.QrService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QrController {
	private final QrService qrService;
	@GetMapping(value = "/api/qr", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> generateQr(@RequestParam("room") int roomNumber) {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
			.body(qrService.generate(roomNumber));
	}
}
