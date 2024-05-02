package com.sds.movieapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Spring 3.0부터 Spring bean xml 대신 어노테이션 기반 설정 지원

@Configuration
public class SecurityConfig {
	
	// Spring이 지원하는 단방향 암호화(해시) 객체
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	// <bean id="" class ="SecurityFilterChain">
	// 	<constructor-args value=""/>
	// </bean>
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		// 필터링할 uri 명시
		httpSecurity
			.authorizeHttpRequests(
					(auth) -> auth
					.requestMatchers("/site/**","/").permitAll()
					.requestMatchers("/member/loginform", "/member/login","/member/joinform","/member/join").permitAll() // 로그인이 필요없는 url
					.requestMatchers("/cs/notice/list").hasRole("user") // 권한이 있는 사용자만이 접근 가능: 회원가입시 지정
					.anyRequest().authenticated() // 위에 명시된 항목 외에는 로그인 요구
			);
			
		httpSecurity
		.formLogin((auth)->
			auth.loginPage("/member/loginform").loginProcessingUrl("/member/login")
				.usernameParameter("uid")
				.passwordParameter("password")
		);
		
		// 토큰 비활성화
		httpSecurity.csrf((auth->auth.disable()));
		
		return httpSecurity.build();
	}
	
}
