package com.sds.newsapp.comments;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 클라이언트의 댓글 요청을 처리하는 서블릿
public class ListServlet extends HttpServlet {
		
	CommentsDAO commentsDAO;
	
	public ListServlet() {
		commentsDAO = new CommentsDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 파라미터 받기
		String news_idx = request.getParameter("news_idx");
		
		List<Comments> list = commentsDAO.selectAllByFkey(Integer.parseInt(news_idx));
		
		// http통신에서 객체 자체를 보낼 수 없다
		// 특히 아래의 코드는 단순 변수이다
		// http 통신에서는 모두 문자열로 취급
		
		// JSON 자체를 자바 코드에서 사용 불가. JSON은 단순 문자열 데이터 포맷
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		sb.append("\"commentsList\" : [");
		for(int i=0; i<list.size(); i++) {
			Comments comments = list.get(i); // DTO 꺼내기
			
			sb.append("{");
			sb.append("\"comments_idx\": "+comments.getComments_idx()+",");
			sb.append("\"msg\" : \""+comments.getMsg()+"\",");
			sb.append("\"cwriter\" : \""+comments.getCwriter()+"\",");
			sb.append("\"cregdate\" : \""+comments.getCregdate()+"\","); 
			sb.append("\"news_idx\" : "+comments.getNews_idx()+"");
			if(i<list.size()-1)
				sb.append("},");
			else
				sb.append("}");
		}
		sb.append("]");
		sb.append("}");
		
		out.print(sb.toString());
	}
}
