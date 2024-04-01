package com.sds.mvcproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 이미지 클라이언트의 요청을 대표 컨트롤러인 DispatcherServlet에서 받고 있기에
// 하위 컨트롤러들은 서블릿일 필요는 없으나 요청을 처리해야 하기에 request, response는 보유

public class TestController implements Controller{
	
	// 하위 컨트롤러가 알맞은 모델에게 업무 전달
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("모델에게 전달");
	}
}
