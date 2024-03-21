package com.sds.newsapp.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 수정 요청을 처리하는 서블릿 정의

public class EditServlet extends HttpServlet {
	
	NewsDAO newsDAO;

	public EditServlet() {
		newsDAO = new NewsDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// out 스트림 객체
		response.setContentType("text/html;charset=utf-8"); // 먼저 인코딩 수행
		PrintWriter out = response.getWriter();
		
		// NewsDAO newsDAO = new NewsDAO(); // 생성자로도 가능
		
		request.setCharacterEncoding("utf-8"); // 인코딩
		// 파라미터를 넘겨받아 오라클 업데이트
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String news_idx = request.getParameter("news_idx");
		
		News news = new News(); // 빈 DTO
		news.setNews_idx(Integer.parseInt(news_idx));
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		int result = newsDAO.update(news);
		
		out.print("<script>");
		if(result>0) {
			out.print("alert('수정 성공');");
			out.print("location.href='/news/content.jsp?news_idx="+news_idx+"';"); // 다시 상세페이지 보여주기
		}
		else {
			out.print("alert('수정 실패');");
			out.print("history.back();");
		}
		out.print("</script>");		
	}
}
