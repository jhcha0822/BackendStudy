package com.sds.mvcproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcproject.notice.model.Notice;
import com.sds.mvcproject.notice.model.NoticeDAO;

public class DetailController implements Controller{

	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		Notice notice = noticeDAO.select(notice_idx);
		
		request.setAttribute("notice", notice);
		RequestDispatcher dis = request.getRequestDispatcher("/notice/content.jsp");
	}

}
