package com.sds.mvcproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcproject.notice.model.Notice;
import com.sds.mvcproject.notice.model.NoticeDAO;

public class RegistController implements Controller {
	
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 3) 모델에 업무 전달
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = noticeDAO.insert(notice);
		
		// 글 작성 후 목록처럼 따로 가져갈 것이 없다고 할 때, 포워딩을 해야 하나?
		if(result>0) {
			// request.setAttribute("", notice); // 결과저장
			System.out.println("등록 성공");
			// RequestDispatcher dis = request.getRequestDispatcher("/notice/list.jsp");
			// dis.forward(request, response);
			response.sendRedirect("/board/list.do"); // 재접속 유도
			
		}
		else {
			System.out.println("등록 실패");
		}
	}
}
