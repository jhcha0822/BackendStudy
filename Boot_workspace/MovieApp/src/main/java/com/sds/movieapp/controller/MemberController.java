package com.sds.movieapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.sds.movieapp.sns.NaverLogin;

@Controller
public class MemberController {
	
	@Autowired	
	private NaverLogin naverLogin;
	
	// 로그인 폼 요청 처리
	@GetMapping("/member/loginform")
	public String getLoginForm() {
		
		return "member/login";
	}
	
	// 네이버 서버에서 들어온 콜백 요청 처리
	// 결과 처리 후 로그인을 요청한 사용자가 보게될 화면으로 반환(html) -> MAV or String
	@GetMapping("/member/sns/naver/callback")
	public ModelAndView naverCallback(String code) {
		
		// token 요청을 위한 Post header & body 구성
		String token_url = naverLogin.getToken_request_url();
		
		// body 구성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("code", code);
		params.add("client_id", naverLogin.getClient_id());
		params.add("client_secret", naverLogin.getClient_secret());
		params.add("redirect_uri", naverLogin.getRedirect_uri()); //콜백 주소 
		params.add("grant_type", naverLogin.getGrant_type());
		params.add("state", naverLogin.getState());
		
		// header 구성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded"); // form 태그 사용시 자동으로 붙음
		
		// header + body
		HttpEntity entity=new HttpEntity(params, headers);
		
		// 비동기 방식으로 post 요청: java언어로 비동기 요청을 구성하는 resttemplate
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(token_url, HttpMethod.POST, entity, String.class);
		
		// 응답 정보에 들어있는 데이터 중 token 꺼내보기
		String body = responseEntity.getBody();
		System.out.println("네이버가 보낸 인증 완료 정보는 "+body);
		
		return null;
	}
	
	
}
