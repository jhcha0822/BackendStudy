package com.sds.movieapp.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Spring 3.0부터 Spring bean xml 대신 어노테이션 기반 설정 지원

/*
 <bean id="msg" class="SecurityFilterChain">
 	<constructor-args value="바보"/>
 </bean> 
*/ 

@Configuration
public class SecurityConfig {
	
//	//스프링이 지원하는 단방향 암호화(해시) 객체 등록
//	@Bean 
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	//스프링이 지원하는 단방향 암호화(해시) 객체 등록
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// AuthenticationManager 는 직접 new 할 수 없기 때문에, AuthenticationConfiguration 객체를 통해 인스턴스를 얻어와야 한다
	// SpringSecurity 5.5 쯤부터 AuthenticationConfiguration는 autowired가 지원됨
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	// <bean id="" class ="SecurityFilterChain">
	// 	<constructor-args value=""/>
	// </bean>
	
	// 기존 spring mvc 에서 AOP를 이용하여 uri를 걸러낸 작업 
	// 스프링 부트 시큐리티에서는 보다 쉽고 체계적으로 지원
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
			.requestMatchers("/rest/member/authform/**").permitAll()
			.requestMatchers("/member/sns/naver/callback").permitAll()
			.requestMatchers("/member/sns/kakao/callback").permitAll()
			
			//영화관련 
			.requestMatchers("/movie/detail").hasAnyAuthority("user")
//			.requestMatchers("/movie/comments").hasAnyAuthority("user")
//			.requestMatchers("/movie/recommend/list").hasAnyAuthority("user")
			
			.anyRequest().authenticated()
		);
		
		// 개발자가 정의한 필터가 적용되도록 등록
		// 기존 session방식의 로그인(form login) 기능을 비활성화
		httpSecurity.formLogin((auth)->auth.disable());
		
		// 기존 UsernamePasswordAuthenticationFilter를 대신할 객체 명시
		CustomAuthenticationFilter customFilter = new CustomAuthenticationFilter(authenticationManager());
		customFilter.setFilterProcessesUrl("/member/login");
		httpSecurity.addFilterAt(customFilter, UsernamePasswordAuthenticationFilter.class);
			
		// 로그인 처리
//		httpSecurity
//		.formLogin((auth)->
//			auth.loginPage("/member/loginform")
//			개발자가 정의한 핸들러 등록
//			.successHandler(loginEventHandler()) // 개발자가 정의한 핸들러
//			.loginProcessingUrl("/member/login")
//			.usernameParameter("uid")
//			.passwordParameter("password")
//		);
		
		// 로그아웃 처리
		httpSecurity
		.logout(logout -> logout
			.logoutUrl("/member/logout") //로그아웃 요청 페이지경로
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
//	@Bean 
//	public AuthenticationSuccessHandler loginEventHandler() {
//		return new LoginEventHandler();
//	}
	
}
