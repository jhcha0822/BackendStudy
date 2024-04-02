package com.sds.model2app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.model2app.model.board.BoardDAO;

// 목록 요청 처리 컨트롤러
public class ListController implements Controller{

	BoardDAO boardDAO = new BoardDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 3)
		List boardList = boardDAO.selectAll();
		
		// 4)
		request.setAttribute("boardList", boardList);
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/view/board/list";
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return true;
	}

}
