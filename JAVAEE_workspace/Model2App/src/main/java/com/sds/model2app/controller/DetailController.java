package com.sds.model2app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.model2app.domain.Board;
import com.sds.model2app.model.board.BoardDAO;

// 상세보기 요청 처리 컨트롤러
public class DetailController implements Controller {

	BoardDAO boardDAO = new BoardDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 3)
		int board_idx = Integer.parseInt(request.getParameter("board_idx"));
		Board board = boardDAO.select(board_idx);
		
		// 4)
		request.setAttribute("board", board);
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/view/board/detail";
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return true;
	}

}
