package com.sds.spring.model.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.spring.domain.Board;
import com.sds.spring.exception.BoardDMLException;
import com.sds.spring.mybatis.MybatisManager;

// 설정 파일의 context:component-scan에 의해 메모리에 올려질 인스턴스의 대상이 된다
@Repository
public class MybatisBoardDAO implements BoardDAO {

	@Autowired
	private MybatisManager manager;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		List list = null;
		
		SqlSession sqlSession = manager.getSqlSession();
		list = sqlSession.selectList("Board.selectAll");
		
		manager.release(sqlSession);
		
		return list;
	}

	@Override
	public Board select(int board_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Board board) throws BoardDMLException {
		// TODO Auto-generated method stub
		SqlSession sqlSession = manager.getSqlSession();
		int result = sqlSession.insert("Board.insert", board);
		result = 0; //예외 확인용
		if(result>0)
			sqlSession.commit();
		else {
			System.out.println("예외 발생시킬 예정");
			new BoardDMLException("글 등록에 실패하였습니다"); // 에러를 생성하여 전가
		}
		manager.release(sqlSession);
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Board board) {
		// TODO Auto-generated method stub
		
	}

}
