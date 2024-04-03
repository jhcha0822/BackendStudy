package com.sds.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.sds.springmvc.domain.Board;
import com.sds.springmvc.model.board.BoardDAO;

// 등록 요청 처리 컨트롤러
public class RegistController implements Controller {
	BoardDAO boardDAO = new BoardDAO();

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		int result = boardDAO.insert(board);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/list");
		
		return mav;
	}
}
