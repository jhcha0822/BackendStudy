package com.sds.testapp.model.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.testapp.domain.Board;
import com.sds.testapp.exception.BoardException;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private MybatisBoardDAO mybatisBoardDAO;
	
	@Override
	public List selectBySearch(String title) {
		// TODO Auto-generated method stub
		return mybatisBoardDAO.selectBySearch(title);
	}
	
	@Override
	public List selectAll(Map map) {
		// TODO Auto-generated method stub
		return mybatisBoardDAO.selectAll(map);
	}

	@Override
	public Board select(int board_idx) {
		// TODO Auto-generated method stub
		return mybatisBoardDAO.select(board_idx);
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return mybatisBoardDAO.getTotalCount();
	}
	
	@Override
	public void insert(Board board) throws BoardException {
		// TODO Auto-generated method stub
		int result = mybatisBoardDAO.insert(board);
		if(result < 1)
			throw new BoardException("글 등록 실패");
	}

	@Override
	public void update(Board board) throws BoardException {
		// TODO Auto-generated method stub
		int result = mybatisBoardDAO.update(board);
		if(result < 1)
			throw new BoardException("글 수정 실패");
	}

	@Override
	public void delete(Board board) throws BoardException {
		// TODO Auto-generated method stub
		int result = mybatisBoardDAO.delete(board);
		if(result < 1)
			throw new BoardException("글 삭제 실패");
	}

}
