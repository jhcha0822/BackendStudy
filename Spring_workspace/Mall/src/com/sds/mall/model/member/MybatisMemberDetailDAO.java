package com.sds.mall.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.MemberDetail;
import com.sds.mall.exception.MemberException;

@Repository
public class MybatisMemberDetailDAO implements MemberDetailDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(MemberDetail memberDetail) throws MemberException {
		// TODO Auto-generated method stub
		int result = sqlSessionTemplate.insert("MemberDetail.insert", memberDetail);
		if(result<1) {
			throw new MemberException("회원 세부 정보 테이블에 입력 실패");
		}		
	}
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MemberDetail select(int member_detail_idx) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update(MemberDetail memberDetail) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(MemberDetail memberDetail) {
		// TODO Auto-generated method stub
		
	}
}
