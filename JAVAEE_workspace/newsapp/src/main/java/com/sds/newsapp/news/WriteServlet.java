package com.sds.newsapp.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 클라이언트의 글쓰기 요청을 받아 오라클에 직접 넣지 않고 DAO에 전달
// DAO는 서버에서 실행되거나 클라이언트의 파라미터를 받을 수 없기 때문
// DAO는 오직 데이터베이스의 CRUD만을 수행하는 중립적 객체임

public class WriteServlet extends HttpServlet {

	NewsDAO newsDAO = new NewsDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// out 스트림 객체
		response.setContentType("text/html;charset=utf-8"); // 먼저 인코딩 수행
		PrintWriter out = response.getWriter();

		// 파라미터 받기
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		// DAO에게 일 시키기
		News news = new News(); // DTO 인스턴스 생성
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		int result = newsDAO.insert(news);
		
		out.print("<script>");
		if(result>0) {
			out.print("alert('등록 성공');");
			out.print("location.href='/news/list.jsp';");
		}
		else {
			out.print("alert('등록 실패');");
			out.print("history.back();");
		}
		out.print("</script>");
	}
}
