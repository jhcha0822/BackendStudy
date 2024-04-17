package com.sds.mall.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.mall.domain.Member;
import com.sds.mall.domain.MemberDetail;
import com.sds.mall.exception.MemberException;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private MemberDetailDAO memberDetailDAO;
	
	// 두 DAO에게 일을 시키고 하나라도 RuntimeException 발생 시 rollback 자동 처리
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void regist(Member member) throws MemberException {
		// TODO Auto-generated method stub
		memberDAO.insert(member); // 회원 테이블에 먼저 insert 후 pk를 자동으로 DTO에 채워넣음
		
		// member 안 memberDetail 접근
		MemberDetail memberDetail = member.getMemberDetail();
		
		// memberDetail이 보유한 member에 member_idx값을 넣기
		Member dto = new Member();
		dto.setMember_idx(member.getMember_idx());
		memberDetail.setMember(dto); // 상세정보에 생성된 dto 주입		
		
		memberDetailDAO.insert(member.getMemberDetail());
	}

	@Override
	public Member login(Member member) throws MemberException {
		// TODO Auto-generated method stub
		return memberDAO.login(member);
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
	public void edit(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Member member) {
		// TODO Auto-generated method stub
		
	}


}
