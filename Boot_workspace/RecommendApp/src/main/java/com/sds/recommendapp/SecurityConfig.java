package com.sds.recommendapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
			.requestMatchers("/site/**").permitAll()      	// 정적 자원 uri
			.requestMatchers("/").hasAnyAuthority("USER")  	// 추천 사이트 
			
			.anyRequest().authenticated()
		);
			
		// 로그인 처리
		httpSecurity
		.formLogin((auth)->
			auth.loginPage("http://localhost:7979/movieapp/member/loginform")
			// 개발자가 정의한 핸들러 등록
			.loginProcessingUrl("/member/login")
			.usernameParameter("uid")
			.passwordParameter("password")
		);
		
		// 로그아웃 처리
		httpSecurity
		.logout(logout -> logout
			.logoutUrl("http://localhost:7979/movieapp/member/logout") //로그아웃 요청 페이지경로
			.logoutSuccessUrl("/") //로그아웃 처리 후, 보여질 링크 
			.invalidateHttpSession(true) //세션 무효화 
			.clearAuthentication(true) //권한 없애기 
			.deleteCookies("JSESSIONID")
		);
		
		// 토큰 비활성화
		httpSecurity.csrf((auth->auth.disable()));
		
		return httpSecurity.build();
	}
	
	// OAuth user와 security를 이용한 홈페이지 로그인 유저가 session에 공통의 memberDTO를 갖고 있게 한다면
	// 회원 정보를 꺼내올 때 member로 통일 가능하다
	// 따라서 security에 모든것을 맡기지 않고, 로그인하는 시점을 낚아채서 session에 memberDTO를 심어놓자
	@Bean 
	public AuthenticationSuccessHandler loginEventHandler() {
		return new LoginEventHandler();
	}
	
}
