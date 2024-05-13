package com.sds.movieapp;

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
					.requestMatchers("/site/**","/").permitAll()
					.requestMatchers("/member/loginform", "/member/login","/member/joinform","/member/join").permitAll() // 로그인이 필요없는 url
					// role은 권한의 집합을 의미하며, hasRole() 메서드는 role을 사용하게 되고, 내부적으로 ROLE_ 접두어가 붙음
					// .requestMatchers("/cs/notice/list").hasRole("user") // 권한이 있는 사용자만이 접근 가능: 회원가입시 지정
					// .requestMatchers("/cs/notice/list", "/cs/notice/writeform").hasAuthority("user") // 접두어가 없는 권한을 이용

					// .requestMatchers("/cs/notice/**").hasAuthority("user")
					.requestMatchers("/cs/notice/**").permitAll()
					
					.requestMatchers("/member/sns/naver/callback").permitAll()
					.requestMatchers("/member/sns/kakao/callback").permitAll()
					.requestMatchers("/rest/member/authform/**").permitAll()
					
					.requestMatchers("/movie/comments").hasAnyAuthority("user")
					.requestMatchers("/movie/recommend/list").hasAnyAuthority("user")
					
					.anyRequest().authenticated() // 위에 명시된 항목 외에는 로그인 요구
					
			);
			
		httpSecurity
		.formLogin((auth)->
			auth.loginPage("/member/loginform")
				// 개발자가 정의한 핸들러 등록
				.successHandler(loginEventHandler())
				.loginProcessingUrl("/member/login")
				.usernameParameter("uid")
				.passwordParameter("password")
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
