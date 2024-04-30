package com.sds.movieapp.sns;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

// 외부 설정 파일의 파라미터를 불러와 가지고 다니거나, 요청 주소를 생성함
@Component
@Data
public class NaverLogin {
	
	@Value("${sns.naver.endpoint}")
	private String endpoint;
	
	@Value("${sns.naver.client_id}")
	private String client_id;
	
	@Value("${sns.naver.client_secret}")
	private String client_secret;
	
	@Value("${sns.naver.redirect_uri}")
	private String redirect_uri;
	
	@Value("${sns.naver.response_type}")
	private String response_type;
	
	@Value("${sns.naver.state}")
	private String state;
	
	@Value("${sns.naver.token_request_url}")
	private String token_request_url;
	
	@Value("${sns.naver.grant_type}")
	private String grant_type;
	
	@Value("${sns.naver.userinfo_url}")
	private String userinfo_url;

	
	//로그인 요청 시 가져갈 파라미터 문자열 
	public String getGrantUrl() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(endpoint+"?client_id="+client_id);
		sb.append("&redirect_uri="+redirect_uri);
		sb.append("&response_type="+response_type);
		sb.append("&state="+state);
		
		return sb.toString();
	}
	
}
