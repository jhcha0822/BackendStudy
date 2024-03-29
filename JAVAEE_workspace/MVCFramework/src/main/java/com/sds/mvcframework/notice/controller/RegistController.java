package com.sds.mvcframework.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcframework.controller.Controller;
import com.sds.mvcframework.notice.model.Notice;
import com.sds.mvcframework.notice.model.NoticeDAO;

public class RegistController implements Controller {

	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 3) 로직 객체 model에 일 전달
		request.setCharacterEncoding("utf-8");

		Notice notice = new Notice();
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = noticeDAO.insert(notice);
		
		System.out.println(result); // 1: 성공
	}

}
