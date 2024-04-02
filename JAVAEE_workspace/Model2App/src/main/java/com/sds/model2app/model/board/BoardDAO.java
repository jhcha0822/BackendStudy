package com.sds.model2app.model.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sds.model2app.domain.Board;
import com.sds.model2app.model.mybatis.MybatisManager;

// 기존 JDBC를 직접 핸들링하지 안혹 mybatis 프레임워크를 이용하는 DAO

public class BoardDAO {
	
	MybatisManager manager = MybatisManager.getInstance(); // SqlSessionFactory 보유
	
	// insert
	public int insert(Board board) {
		int result = 0;
		
		SqlSession sqlSession = null; // Mybatis의 쿼리 수행 객체
		
		sqlSession = manager.getSqlSession();
		
		// result = sqlSession.insert("com.sds.model2app.domain.Board.insert", board);
		result = sqlSession.insert("Board.insert", board);
		sqlSession.commit(); // autoCommit=false 이기에 수동 커밋
		
		manager.release(sqlSession); // 반납
		
		return result;
	}
	
	public List<Board> selectAll() {
		List list = null;
		
		SqlSession sqlSession = null;
		sqlSession = manager.getSqlSession();
		
		list = sqlSession.selectList("Board.selectAll");
		
		manager.release(sqlSession);
		
		return list;
	}
	
	public Board select(int board_idx) {
		Board board = null;
		
		SqlSession sqlSession = null;
		sqlSession = manager.getSqlSession();
		board = sqlSession.selectOne("Board.select", board_idx);
		
		manager.release(sqlSession);
		
		return board;
	}
	
	public int update(Board board) {
		int result = 0;
		
		SqlSession sqlSession = null;
		sqlSession = manager.getSqlSession();
		result = sqlSession.update("Board.update", board);
		sqlSession.commit();
		
		manager.release(sqlSession);
		
		return result;
	}
	
	public int delete(int board_idx) {
		int result = 0;
		
		SqlSession sqlSession = null;
		sqlSession = manager.getSqlSession();
		result = sqlSession.delete("Board.delete", board_idx);
		sqlSession.commit();
		
		manager.release(sqlSession);
		
		return result;
	}
}
