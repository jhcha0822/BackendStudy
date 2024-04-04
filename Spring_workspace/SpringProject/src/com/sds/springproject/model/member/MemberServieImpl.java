package com.sds.springproject.model.member;

import org.apache.ibatis.session.SqlSession;

import com.sds.springproject.domain.Bio;
import com.sds.springproject.model.bio.BioDAO;
import com.sds.springproject.mybatis.MybatisManager;

// 기존에 하위 컨트롤러가 가지고 있었던 모델 영역의 코드를 처리
public class MemberServieImpl implements MemberService {

	private MybatisManager manager;
	private MemberDAO memberDAO; // 느슨하게 상위 자료형 보유(DI)
	private BioDAO bioDAO;
	
	public void setManager(MybatisManager manager) {
		this.manager = manager;
	}
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	public void setBioDAO(BioDAO bioDAO) {
		this.bioDAO = bioDAO;
	}
		
	public int regist(Bio bio) {
		int result;
		
		SqlSession sqlSession = null;
		sqlSession = manager.getSqlSession();
		
		// 회원 등록 후 성공하면 취득한 member_idx로 bio도 insert
		result = memberDAO.insert(sqlSession, bio.getMember());
		
		if(result > 0) {
			result = bioDAO.insert(sqlSession, bio);
			if(result > 0) {
				sqlSession.commit(); // 트랜젝션 커밋
			}
		}
		
		manager.release(sqlSession);
		
		return 0;
	}
	
}
