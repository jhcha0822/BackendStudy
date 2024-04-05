package com.sds.springproject.model.member;

import org.apache.ibatis.session.SqlSession;

import com.sds.springproject.domain.Member;
import com.sds.springproject.mybatis.MybatisManager;

public class MybatisMemberDAO implements MemberDAO{
	
	// MybatisManager manager = MybatisManager.getInstance(); // SingleTon
	
	/*
	private MybatisManager manager; // DI로 받기
	
	// Spring Container에게 인스턴스를 주입받기 위한 Setter 메서드 정의
	public void setManager(MybatisManager manager) {
		this.manager = manager;
	}
	*/
	// 트랜젝션 상황이기에 manager를 주입받지 않는다.
	
	// 회원 등록
	public int insert(Object obj, Member member) {
		int result = 0;

		// SqlSession sqlSession = null; // mybatis의 쿼리 수행 객체
		SqlSession sqlSession = (SqlSession)obj; // 상위 인터페이스는 중립적이므로, 첫 매개변수가 Object형으로 들어옴. 따라서 형변환 필요
		
		// sqlSession = manager.getSqlSession(); // Session 하나 꺼내기
		result = sqlSession.insert("Member.insert", member); // 쿼리, (namespace id, 매개변수=DTO)
		
		// if(result>0)
			// sqlSession.commit(); 트랜젝션 상황이기에 commit하지 않는다
		
		// manager.release(sqlSession); // 반납
		
		return result;
	}
	
	
}
