package com.sds.mvcproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcproject.notice.model.Notice;
import com.sds.mvcproject.notice.model.NoticeDAO;

public class EditController implements Controller {
	
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		notice.setNotice_idx(notice_idx);
		
		int result = noticeDAO.update(notice);
		
		//수정 후, 상세보기를 재접속하도록 유도해야 하지만,  예를 들어 /board/detail.do?notice_idx=7
		//URL을 구성할 경우, ??notice_idx=7 문자열이 매핑 파일에 존재하지 않는 문자열이기 때문에 
		//매핑에 실패하게 된다..
		//해결책? 요청을 유지하면서 즉 포워딩하여 DTO를 가지고 가자 content.jsp로... 
		//포워딩 처리하자 
		request.setAttribute("notice", notice);
		RequestDispatcher dis = request.getRequestDispatcher("/notice/content.jsp"); //상세페이지로 포워딩
		dis.forward(request, response); //포워딩 시작

		
		
	}
}
