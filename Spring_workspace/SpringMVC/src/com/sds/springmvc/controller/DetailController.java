package com.sds.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.sds.springmvc.domain.Board;
import com.sds.springmvc.model.board.BoardDAO;

// 상세보기 요청 처리 컨트롤러
public class DetailController implements Controller{

	BoardDAO boardDAO = new BoardDAO();
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String board_idx = request.getParameter("board_idx");
		
		Board board = boardDAO.select(Integer.parseInt(board_idx));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("board/content");
		
		return mav;
	}

}
