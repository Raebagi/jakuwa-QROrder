package com.raebagi.order.controller; // 본인 프로젝트 패키지 경로에 맞게 수정하세요

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class QrController {

	@GetMapping(value = "/api/qr", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> generateQr(@RequestParam("room") int roomNumber) {
		try {
			// 본인 컴퓨터의 로컬 IP 주소와 포트번호(8080)로 변경해주세요
			String baseUrl = "https://operable-commerce-velvet.ngrok-free.dev/order";
			String targetUrl = baseUrl + "?room=" + roomNumber;

			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			// QR 코드 크기 (가로 250px, 세로 250px)
			BitMatrix bitMatrix = qrCodeWriter.encode(targetUrl, BarcodeFormat.QR_CODE, 250, 250);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(outputStream.toByteArray());

		} catch (WriterException | IOException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
}