package com.sds.mall.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.mall.sns.GoogleLogin;

// 회원 관련 요청 중 비동기 Rest 요청만 처리
@RestController
public class RestMemberController {
	
	@Autowired
	private GoogleLogin googleLogin;
	
	
	// google 인증화면 주소에 대한 요청 처리
	// @ResponseBody: RestController라서 자동으로 적용	
	@GetMapping("/rest/member/authform/google")
	public ResponseEntity getGoogleUrl() {
		
		String url = googleLogin.getGrantUrl();
		ResponseEntity entity = ResponseEntity.ok(url); 
		
		return entity;
	}	
	
	// naver 인증화면 주소에 대한 요청 처리
	
	
	
	// kakao 인증화면 주소에 대한 요청 처리
	
	
	
}
