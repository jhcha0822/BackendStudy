package com.sds.mall.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sds.mall.domain.Member;
import com.sds.mall.exception.UnAuthorizedException;

// 회원에게만 제공되는 요청에 대해 로그인 여부를 체크하는 Aspect
public class LoginAspect {
	
	// 보안 인증이 필요한 요청인지 여부를 알려주는 메서드 true: 회원인증 필요
	public boolean isSecureUri(String uri) {

		// 아래 배열에 들어있는 URI는 인증이 필요하다
		String[] secureURI = {
			"/order/cart/list",	
			"/member/mypage/main",	
			"/order/cart/regist",
		};
		
		int count = 0;
		for(String str : secureURI) {
			if(str.equals(uri))
				count++;
		}
		
		if(count>0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Object sessionCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		Object obj = null; // 하위 컨트롤러들이 반환하게될 결과 객체(ModelAndView, String)
		
		// 이 Aspect가 가져온 요청이ㅔ 대한 URI 정보가 로그인이 필요한 서비스라면
		// 세션에 member가 들어있는지: 로그인 인증을 거친 회원인지 확인
		HttpServletRequest request= ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String uri = request.getRequestURI();
		
		if(isSecureUri(uri)) {
			// 세션안에 member 여부 확인
			HttpSession session = request.getSession(); // 요청 객체로 부터 이 요청과 연관된 세션 얻기
			Member member = (Member)session.getAttribute("member");
			
			if(member != null) { 
				obj = joinPoint.proceed(); // 가던길 가게
			}
			else {
				throw new UnAuthorizedException("로그인이 필요한 서비스입니다");
			}
		} else {
			obj = joinPoint.proceed();	
		}
		
		return obj;
	}
	
}
