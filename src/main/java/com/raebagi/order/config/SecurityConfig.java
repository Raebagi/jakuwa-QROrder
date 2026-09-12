package com.raebagi.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
		throws Exception {

		http
			.authorizeHttpRequests(auth -> auth
				// 관리자 화면과 관리자 API
				.requestMatchers("/admin", "/admin/**", "/api/admin/**")
				.hasRole("ADMIN")

				// 외부에서 DB 콘솔에 접근하지 못하도록 차단
				.requestMatchers("/h2-console", "/h2-console/**")
				.denyAll()

				// 고객 주문 화면, 메뉴 조회 등
				.anyRequest()
				.permitAll()
			)
			.formLogin(form -> form
				// Spring Security의 기본 로그인 화면 사용
				.defaultSuccessUrl("/admin", true)
				.permitAll()
			)
			.logout(logout -> logout
				.logoutSuccessUrl("/login?logout")
			);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(
		@Value("${app.admin.username}") String username,
		@Value("${app.admin.password}") String password,
		PasswordEncoder passwordEncoder) {

		UserDetails admin = User.withUsername(username)
			.password(passwordEncoder.encode(password))
			.roles("ADMIN")
			.build();

		return new InMemoryUserDetailsManager(admin);
	}
}