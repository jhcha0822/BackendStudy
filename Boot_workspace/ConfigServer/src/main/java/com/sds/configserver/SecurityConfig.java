package com.sds.configserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// Spring이 지원하는 단방향 암호화(해시) 객체
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf(auth -> auth.disable());
		httpSecurity.authorizeHttpRequests(
				auth->auth.anyRequest().authenticated()
		);
		httpSecurity.httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
	}
	
	// 개발의 편의상 데이터베이스의 설정 서버에 관리자 정보를 저장하는 것이 아닌, 메모리상에 인증 정보 보관
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user = User.builder()
				.username("config")
				.password(bCryptPasswordEncoder().encode("1234"))
				.roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(user);
	}
	
}
