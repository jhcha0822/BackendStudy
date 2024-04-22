package com.sds.mall.sns;

import lombok.Data;

// String으로 된 JSON 문자열을 파싱한 결과인 access_token을 보관할 객체
@Data
public class GoogleOAuthToken {

	private String access_token;
	private String expires_in;
	private String scope;
	private String token_type;
	private String id_token;
	
}
