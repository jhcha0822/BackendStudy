package com.sds.poolapp.notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비동기방식의 요청을 받는게 아니므로, 클라이언트의 요청에 응답할 데이터는 일부 데이터가 아닌
		// 완전한 페이지를 만들어 응답 정보를 구성해야 함
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 파라미터 받기
		
		
	}
}
