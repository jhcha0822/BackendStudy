package com.sds.model2app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.model2app.model.board.BoardDAO;

// 삭제 요청 처리 컨트롤러
public class DeleteController implements Controller{

	BoardDAO boardDAO = new BoardDAO();
	String viewName;
	boolean isForward;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 3)
		String board_idx = request.getParameter("board_idx");
		int result = boardDAO.delete(Integer.parseInt(board_idx));
		
		// 4)
		if(result>0) {
			viewName = "/view/board/delete";
			isForward = false; // 재접속 redirect 유도
		} else {
			request.setAttribute("msg", "글 삭제 실패");
			viewName = "/view/board/error";
			isForward = true; // 메시지를 살려서 error.jsp까지 가져가야 하기에 포워딩 필요
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
		return false;
	}

}
