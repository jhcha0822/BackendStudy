package com.sds.mvcproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcproject.notice.model.NoticeDAO;

// 게시판의 목록 요청을 처리하는 컨트롤러
public class ListController implements Controller{
	
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("게시판 목록 요청 DAO에게 전달");
		
		// 3) 알맞은 model 객체에게 업무 전달
		List noticeList = noticeDAO.selectAll();
		
		// 4) jsp로 전달할 데이터가 있으므로 반환받은 noticeList를 view로 전달
		// 응답하지 않았다면 request, response가 살아있기에
		// request 객체에 noticeList를 담아 jsp에게 포워딩
		// 포워딩 담당 객체인 RequsetDispatcher 객체를 얻기
		request.setAttribute("noticeList", noticeList); // 요청 객체에 데이터 넣어두기
		RequestDispatcher dis=request.getRequestDispatcher("/notice/list.jsp");//포워딩할 주소
		dis.forward(request, response); //뷰 페이지로 포워딩  
	}
}
