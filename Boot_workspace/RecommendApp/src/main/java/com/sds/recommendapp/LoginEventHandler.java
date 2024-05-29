package com.sds.recommendapp;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.sds.recommendapp.domain.CustomUserDetails;
import com.sds.recommendapp.domain.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Spring security가 로그인 처리하는 시점을 알려주는 이벤트 메서드를 통해 개발자가 원하는 로그인 처리 커스터마이징 가능
// session에 member를 담기 위함: 로그인 기술과의 통합(OAuth는 anonymoususer를 모른다)
public class LoginEventHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {    
	
	//유저가 스프링 시큐리티 기반으로 로그인을 성공하는 시점
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 현재 로그인한 사용자로부터 member를 추출
		// 로그인 성공한 spring security user는 CustomUserDetails라는 객체를 인스턴스로 보유한 상태
		// 해당 객체 내부의 memberDTO를 꺼내 session에 담자
		CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
		Member member = userDetails.getMember();
		
		// session에 member 넣기
		HttpSession session = request.getSession();
		session.setAttribute("member", member);
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
