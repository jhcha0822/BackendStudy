package com.sds.newsapp.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 클라이언트의 삭제 요청을 처리하는 서블릿
// 직접 db를 삭제하지 않고 DAO에게 요청
public class DeleteServlet extends HttpServlet {
	
	// NewsDAO newsDAO = new NewsDAO();
	
	NewsDAO newsDAO;
	
	public DeleteServlet() {
		newsDAO = new NewsDAO();
	}
	
	// 파라미터가 1개이기에 get으로 요청
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 파라미터 받기, 한글 등의 데이터가 전달되어 오는 것이 아니기에 인코딩 처리 안함
		String news_idx = request.getParameter("news_idx");
		
		int result = newsDAO.delete(Integer.parseInt(news_idx));
		
		out.print("<script>");
		if(result>0) {
			out.print("alert('삭제 성공');");
			out.print("location.href='/news/list.jsp';");
		}
		else {
			out.print("alert('삭제 실패');");
			out.print("history.back();");
		}
		out.print("</script>");
	}
}
