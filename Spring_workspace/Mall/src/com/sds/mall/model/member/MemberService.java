package com.sds.mall.model.member;

import java.util.List;

import com.sds.mall.domain.Member;
import com.sds.mall.domain.SnS;

// Transaction 처리를 위한 서비스
// 2개의 테이블: Member, Member_Detail 처리를 위한 트랜젝션

public interface MemberService {
	public void regist(Member member);    // 가입. insert보다 상위의 개념
	public Member login(Member member);   // 로그인
	public Member isSnSMember(Member member);
	public List selectAll(); 		      // 회원 목록
	public List selectAllBySnsIdx(int sns_idx);
	public Member select(int member_idx); // 회원정보 1건 가져오기
	public void edit(Member member);	  // 회원정보 수정. update보다 상위의 개념
	public void remove(Member member);	  // 회원 삭제. delete보다 상위의 개념
	
	// SNS 정보 가져오기
	public SnS selectByName(String name);
}
