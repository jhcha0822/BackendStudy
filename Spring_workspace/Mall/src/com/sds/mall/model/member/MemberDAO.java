package com.sds.mall.model.member;

import java.util.List;

import com.sds.mall.domain.Member;

// 회원 관련 DAO 중의 최상위 인터페이스
public interface MemberDAO {
	public void insert(Member member); 	  // 가입
	public Member login(Member member);   // 로그인
	public List selectAll(); 		      // 회원 목록
	public List selectAllBySnsIdx(int sns_idx);
	public Member select(int member_idx); // 회원정보 1건 가져오기
	public void update(Member member);	  // 회원정보 수정
	public void delete(Member member);	  // 회원 삭제
}
