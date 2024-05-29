package com.sds.recommendapp.domain;

import lombok.Data;

@Data
public class Member {

	private int member_idx;
	private String uid;
	private String nickname;
	private String email;
	
	private MemberDetail memberDetail;
	private Sns sns;
	private Role role;
	
}
