package com.sds.movieadmin.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sds.movieadmin.domain.Admin;
import com.sds.movieadmin.exception.UnAuthorizedException;

// 웹 어플리케이션의 모든 컨트롤러의 요청을 낚아채셔 세션이 있는지 확인
// 세션이 없다면 에러를 발생시킴
public class LoginAspect {
	
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Object obj = null; // 하위 컨트롤러가 반환한 리턴값
		
		// request 요청 객체를 이용하여 session을 얻어오고, 그 안에 Admin DTO가 존재하는지 확인
		HttpServletRequest request = null;
		request= ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String uri = request.getRequestURI();
		if(uri.equals("/admin/loginform") || uri.equals("/admin/registform") || uri.equals("/auth/admin")) { // 제외될 uri의 경우
			obj = joinPoint.proceed(); // 호출하려하였던 하위 컨트롤러의 메서드 호출. 예외는 전달
		} else { // 검증이 필요한 uri의 경우
			// 세션 검사
			HttpSession session = request.getSession();
			Admin admin = (Admin)session.getAttribute("admin");
			if(admin != null) { // 로그인이 성공한 관리자
				obj = joinPoint.proceed(); // 하위 컨트롤러의 리턴값(String, MAV 등등)
			} else { // 인증이 되지 않은 접근 상황
				throw new UnAuthorizedException("로그인이 필요한 서비스입니다"); // dispatcherServelt에게 전달
			}
		}
		
		return obj;
	}
	
}
