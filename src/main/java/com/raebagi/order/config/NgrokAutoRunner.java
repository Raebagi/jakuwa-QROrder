package com.raebagi.order.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NgrokAutoRunner {

	@EventListener(ApplicationReadyEvent.class)
	public void runNgrok() {
		// 이미 ngrok이 실행 중이거나 백그라운드 충돌을 방지하기 위해 백그라운드 프로세스로 실행
		String domain = "operable-commerce-velvet.ngrok-free.dev";
		String command = "ngrok http --domain=" + domain + " 8080";

		try {
			ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
			// 필요시 ngrok 실행 출력을 콘솔에서 확인하려면 주석 해제
			// processBuilder.inheritIO();
			processBuilder.start();
			System.out.println(">>> Ngrok 터널 자동 실행 완료: " + domain);
		} catch (IOException e) {
			System.err.println(">>> Ngrok 자동 실행 실패: " + e.getMessage());
		}
	}
}