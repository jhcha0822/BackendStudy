package com.sds.spring.model.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sds.spring.domain.Board;
import com.sds.spring.exception.BoardDMLException;

// 서비스 인터페이스 구현체
@Service
public class BoardServiceImpl implements BoardService {

	// Annotation으로 주입
	// Qualifier("레퍼런스명") 으로 대상 명시 가능
	// 레퍼런스명은 servlet-context에 있는 명칭으로
	@Autowired
	@Qualifier("mybatisBoardDAO")
	private BoardDAO boardDAO;
	
	@Override
	public List selectAll() {
		System.out.println("service의 selectAll() 호출");
		return boardDAO.selectAll();
	}

	@Override
	public Board select(int board_idx) {
		// TODO Auto-generated method stub
		return boardDAO.select(board_idx);
	}

	// 서비스의 insert 메서드를 호출한 컨트롤러에게 BoardDMLException을 전달한다
	@Override
	public void insert(Board board) throws BoardDMLException{
		// TODO Auto-generated method stub
		boardDAO.insert(board);
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		boardDAO.update(board);
	}

	@Override
	public void delete(Board board) {
		// TODO Auto-generated method stub
		boardDAO.delete(board);
	}

}
