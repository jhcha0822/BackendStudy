package com.sds.asyncboard.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 글쓰기 요청을 처리하는 서블릿
public class RegistServlet extends HttpServlet{
	
	BoardDAO boardDAO = new BoardDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("utf-8");;
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		// 파라미터를 DTO에 전달
		Board board = new Board(); //empty 상태
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		// DAO에게 DTO 등록 요청
		int result = boardDAO.insert(board);
		
		if(result>0)
			out.print("Insert Done");
		else
			out.print("Insert Fail");
		
	}
}
