package com.sds.movieapp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sds.movieapp.domain.CustomUserDetails;
import com.sds.movieapp.domain.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

// 사용자명을 저장할 AOP 구성: 모든 컨트롤러에서 사용

// 더이상 사용할 필요가 없다.
// Spring Security를 쓰기 이전, AOP를 이용하여 권한 설정을 사용하였음

@Slf4j
// @Aspect
// @Component
public class AuthAspect {

	@Pointcut("execution(public * com.sds.movieapp.controller..*(..))")
	public void includeExecution() {}
	
	@Pointcut("!execution(public * com.sds.movieapp.controller.Rest*Controller.*(..))")
	public void excludeExecution() {}
	
	@Around("includeExecution() && excludeExecution()")
	public Object checkSession(ProceedingJoinPoint joinPoint) throws Throwable{
		Object obj = null;
		
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		
//		// Spring Security를 통해 로그인한 사용자의 이름 가져오기
//		String nickname=SecurityContextHolder.getContext().getAuthentication().getName();
//		request.setAttribute(nickname, "nickname");
//
//		// Spring에서 session 가져오기
//		// HttpSession session = request.getSession();
//		// session.setAttribute("nickname", nickname);
		
		// Spring security가 로그인을 처리했기에 Spring Security를 통해 유저 정보를 꺼내
		// detail.html에서 사용할 수 있도록 request 객체에 저장해놓아야 함
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// 로그인 하지 않았을 경우 스프링 시큐리티가 auth.getPrincipal() 반환으로 anonymousUser를 반환: 조건문 사용
		if(auth.getPrincipal() instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal();
			Member member = userDetails.getMember();
			
			request.setAttribute("nickname", member.getNickname());//우측 상단의 사용자명을 위한 처리 
			request.setAttribute("member", member);
			
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
		} 
		
		obj = joinPoint.proceed();
		
		return obj;
	}
}
