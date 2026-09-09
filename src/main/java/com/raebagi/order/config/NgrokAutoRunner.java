package com.raebagi.order.config;

import java.io.IOException;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("local")
@ConditionalOnProperty(
	name = "app.ngrok.enabled",
	havingValue = "true"
)
public class NgrokAutoRunner {

	private final String domain;
	private final int port;

	private Process process;

	public NgrokAutoRunner(
		@Value("${app.ngrok.domain}") String domain,
		@Value("${server.port:8080}") int port
	) {
		this.domain = domain;
		this.port = port;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runNgrok() {
		if (process != null && process.isAlive()) {
			return;
		}

		try {
			process = new ProcessBuilder(
				"ngrok",
				"http",
				"--domain=" + domain,
				String.valueOf(port)
			)
				.inheritIO()
				.start();

			log.info(
				"ngrok 프로세스를 시작했습니다. domain={}, port={}",
				domain,
				port
			);
		} catch (IOException e) {
			log.error(
				"ngrok 실행에 실패했습니다. ngrok 설치와 PATH 설정을 확인하세요.",
				e
			);
		}
	}

	@PreDestroy
	public void stopNgrok() {
		if (process != null && process.isAlive()) {
			process.destroy();
			log.info("ngrok 프로세스를 종료했습니다.");
		}
	}
}