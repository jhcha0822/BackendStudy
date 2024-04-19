package com.sds.mall.client.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 회원 관련 요청 중 비동기 Rest 요청만 처리
@RestController
public class RestMemberController {
	
	// google 인증화면 주소에 대한 요청 처리
	@GetMapping("/rest/member/authform/google")
	public ResponseEntity getGoogleUrl() {
		String url = "https://accounts.google.com/o/oauth2/v2/auth";
		
		ResponseEntity entity = ResponseEntity.ok(url); 
		
		return entity;
	}	
	
	// naver 인증화면 주소에 대한 요청 처리
	
	
	
	// kakao 인증화면 주소에 대한 요청 처리
	
	
	
}
