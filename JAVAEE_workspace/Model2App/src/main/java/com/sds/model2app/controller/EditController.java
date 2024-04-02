package com.sds.model2app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.model2app.domain.Board;
import com.sds.model2app.model.board.BoardDAO;

// 수정 요청 처리 컨트롤러
public class EditController implements Controller{

	BoardDAO boardDAO = new BoardDAO();
	String viewName;
	boolean isForward;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 3)
		// 인코딩: filter에서 진행 완료
		// 파라미터 4개 받기
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String board_idx = request.getParameter("board_idx");
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setContent(content);
		board.setBoard_idx(Integer.parseInt(board_idx));
		
		// 4)
		int result = boardDAO.update(board);
		
		if(result>0) {
			request.setAttribute("board", board);
			viewName = "/view/board/update";
			isForward = true;
		} else {
			request.setAttribute("msg", "글 수정 실패");
			viewName = "/view/board/error";
			isForward = true;
		}
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return viewName;
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return isForward;
	}

}
