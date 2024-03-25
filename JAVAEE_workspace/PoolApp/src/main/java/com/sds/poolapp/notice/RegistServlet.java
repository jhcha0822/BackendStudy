package com.sds.poolapp.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistServlet extends HttpServlet {
	NoticeDAO noticeDAO = new NoticeDAO();
	
	// 클라이언트가 post 방식으로 전송을 시도하기에 doPost 재정의
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트의 파라미터 받기
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		System.out.println("제목은 "+title);
		System.out.println("작성자는 "+writer);
		System.out.println("내용은 "+content);
		
		Notice notice = new Notice();
		
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = noticeDAO.insert(notice);
		
		if(result>0) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}
}
