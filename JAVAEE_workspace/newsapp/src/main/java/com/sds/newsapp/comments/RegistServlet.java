package com.sds.newsapp.comments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 클라이언트의 댓글 등록 요청을 처리하는 서블릿
public class RegistServlet extends HttpServlet{
	
	CommentsDAO commentsDAO = new CommentsDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 파라미터 받기
		request.setCharacterEncoding("utf-8");
		String msg = request.getParameter("msg");
		String cwriter = request.getParameter("cwriter");
		String news_idx = request.getParameter("news_idx");
		
		// DTO의 인스턴스로 DAO에게 파라미터들을 전송
		Comments comments = new Comments(); // empty DTO
		
		comments.setMsg(msg);
		comments.setCwriter(cwriter);
		comments.setNews_idx(Integer.parseInt(news_idx));
		
		int result = commentsDAO.insert(comments); // 댓글 등록
		
		if(result>0)
			out.print("ok");
		else
			out.print("error");
	}
}
