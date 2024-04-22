package com.sds.mall.sns;

import org.springframework.stereotype.Component;

import lombok.Data;

// 구글 로그인 인증 시 가져가야할 파라미터를 이 객체에 담아서 처리
@Data
@Component
public class GoogleLogin {
	
	private String endpoint; // 인증 주소
							 // 클라이언트(웹 사이트 이용자)가 보게될 동의 화면 주소
							 // 구글에서 제공, 변수명은 개발자가 정의
							 // Spring context의 bean의 멤버변수와 연동
	
	private String client_id; 	  // 웹 서비스가 등록된 구글 개발자 콘솔의 앱 ID
	private String client_secret; // 앱 비밀번호
	
	private String redirect_uri;  // 구글로부터 callback받을 uri: 승인된 리디렉션 URI 작성
	
	private String response_type; // 구글로부터 전달받을 파라미터명
	
	private String scope;		  // 사용자로부터 수집하려고 하는 데이터의 범위
	
	
	private String token_request_url; // token 발급 요청 주소
	private String grant_type;		  // oauth 표준을 준수하기에 3사가 같은 명칭 사용
	private String userinfo_url;	  // 회원 정보 요청 시 url
	
	// 동의화면이나 로그인 요청 시 구글에 전송할 파라미터 구성하기
	// 이 url 및 파라미터 구성은 구글 API에서 정해놓은 규칙을 준수함
	// 필수적인 파라미터는 반드시 전송
	public String getGrantUrl() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(endpoint+"?client_id="+client_id);
		sb.append("&redirect_uri="+redirect_uri);
		sb.append("&response_type="+response_type);
		sb.append("&scope="+scope);
		
		return sb.toString();
	}
}
