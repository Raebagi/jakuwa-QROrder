package com.raebagi.order.service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class QrService {
	private final String orderBaseUrl;
	public QrService(@Value("${app.order-base-url}") String orderBaseUrl) {
		URI uri = URI.create(orderBaseUrl);
		if (!("https".equals(uri.getScheme()) || "http".equals(uri.getScheme()))
			|| uri.getHost() == null || uri.getFragment() != null) {
			throw new IllegalArgumentException("유효한 주문 페이지 URL이 필요합니다.");
		}
		this.orderBaseUrl = orderBaseUrl;
	}
	public byte[] generate(int roomNumber) {
		if (roomNumber <= 0) {
			throw new IllegalArgumentException("방 번호는 양수여야 합니다.");
		}
		String target = UriComponentsBuilder.fromUriString(orderBaseUrl)
			.replaceQueryParam("room", roomNumber).build().encode().toUriString();
		try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
			MatrixToImageWriter.writeToStream(new QRCodeWriter().encode(
				target, BarcodeFormat.QR_CODE, 250, 250), "PNG", output);
			return output.toByteArray();
		} catch (WriterException | IOException e) {
			throw new IllegalStateException("QR 코드 생성에 실패했습니다.", e);
		}
	}
}
