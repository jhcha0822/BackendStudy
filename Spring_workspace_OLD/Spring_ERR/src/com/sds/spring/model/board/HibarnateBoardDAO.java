package com.sds.spring.model.board;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.spring.domain.Board;
import com.sds.spring.hibernate.HibernateManager;

// Board 관련 DAO 중 하이버네이트를 이용한 DAO 정의
@Repository
public class HibarnateBoardDAO implements BoardDAO{

	@Autowired
	private HibernateManager manager;
	
	@Override
	public List selectAll() {
		System.out.println("DAO의 selectAll() 호출");
		// Hibernate의 Session을 얻어와 쿼리 실행
		Session session = manager.getSession();
		Query<Board> query = session.createQuery("FROM Board", Board.class); // 모든 레코드를 가져와 DTO 매핑
		
		List list = query.list();
		manager.release(session);
		
		return list;
	}

	@Override
	public Board select(int board_idx) {
		// TODO Auto-generated method stub
		Session session = manager.getSession();
		
		Query<Board> query = session.createQuery("FROM Board WHERE board_idx=:board_idx", Board.class);
		query.setParameter("board_idx", board_idx);

		Board board = (Board)query.uniqueResult(); // 한 건을 가져오는 메서드
		manager.release(session);
		
		return board;
	}

	@Override
	public void insert(Board board) {
		// TODO Auto-generated method stub
		Session session = manager.getSession();
		
		// DML은 트랜젝션 처리함
		session.beginTransaction(); 		// 트랜젝션 시작
		session.save(board); 				// DTO의 property 값들을 DB에 반영
		session.getTransaction().commit(); 	// 트랜젝션 커밋
		
		manager.release(session);
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		Session session = manager.getSession();
		
		// DML은 트랜젝션 처리함
		session.beginTransaction(); 		// 트랜젝션 시작
		session.update(board); 				
		session.getTransaction().commit(); 	// 트랜젝션 커밋
		
		manager.release(session);
	}

	@Override
	public void delete(Board board) {
		// TODO Auto-generated method stub
		Session session = manager.getSession();
		
		// DML은 트랜젝션 처리함
		session.beginTransaction(); 		// 트랜젝션 시작
		session.delete(board); 				
		session.getTransaction().commit(); 	// 트랜젝션 커밋
		
		manager.release(session);
	}

}
