package com.sds.mall.domain;

// 구글 로그인 인증 시 가져가야할 파라미터를 이 객체에 담아서 처리
public class GoogleLogin {
	private String endpoint; // 인증 주소

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	
}
