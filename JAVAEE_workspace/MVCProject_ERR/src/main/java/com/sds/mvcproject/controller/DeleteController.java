package com.sds.mvcproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcproject.notice.model.NoticeDAO;

public class DeleteController implements Controller {
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		noticeDAO.delete(notice_idx);
		
		// 저장할 결과가 없으므로 포워딩 X 클라이언트로 하여금 목록 요청 명령
		response.sendRedirect("/board/list.do");
	}
}
