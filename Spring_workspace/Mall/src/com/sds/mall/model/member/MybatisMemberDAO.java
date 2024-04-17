package com.sds.mall.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Member;
import com.sds.mall.exception.MemberException;

@Repository
public class MybatisMemberDAO implements MemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(Member member) throws MemberException {
		// TODO Auto-generated method stub
		int result = sqlSessionTemplate.insert("Member.insert", member);
		if(result<1) {
			// 실패한 경우 RuntimeException을 일으켜야 스프링에서 트랜젝션 rollback 가능
			throw new MemberException("회원 테이블에 정보 입력 실패");
		}
	}

	@Override
	public Member login(Member member) throws MemberException {
		// TODO Auto-generated method stub
		Member dto = sqlSessionTemplate.selectOne("Member.login", member);
		if(dto==null) { // 로그인 실패
			throw new MemberException("로그인 실패");
		}
		
		return dto;
	}
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAllBySnsIdx(int sns_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member select(int member_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Member member) {
		// TODO Auto-generated method stub
		
	}


}
