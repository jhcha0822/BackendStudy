package com.sds.springproject.model.member;

import com.sds.springproject.domain.Member;

// 객체간의 결합도를 낮추기 위해 MemberDAO 최상위 객체 제공
// MybatisMemberDAO, HibernateMemberDAO 등에서 has-a 로 보유

public interface MemberDAO {
	
	public int insert(Object obj, Member member);
	
}
