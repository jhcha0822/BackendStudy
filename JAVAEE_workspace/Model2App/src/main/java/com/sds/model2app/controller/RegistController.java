package com.sds.model2app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.model2app.domain.Board;
import com.sds.model2app.model.board.BoardDAO;

// 글쓰기 요청을 처리하는 하위 컨트롤러
public class RegistController implements Controller{

	BoardDAO boardDAO = new BoardDAO();
	String viewName;
	boolean isForward;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// filter에서 캐릭터 인코딩 수행해놓았음
		Board board = new Board();
		
		board.setTitle(request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
		board.setContent(request.getParameter("content"));
		
		int result = boardDAO.insert(board);
		// System.out.println("등록 결과:"+result);
		if(result>0) { // 등록 성공
			viewName = "/view/board/regist";
			isForward = false;
		} else {
			String msg = "등록 실패";
			request.setAttribute("msg", msg);
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
