package com.sds.model2app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// test.do 요청을 처리하는 컨트롤러
// 3) 알맞는 모델 객체에 업무 전달
// 4) jsp로 가져갈 것이 있다면 결과를 request에 저장(선택)

public class TestController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 3)
		
		// 4)
		request.setAttribute("msg", "model2 framework test");
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/view/test";
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return true; // 결과가 없으면(false) redirect, 있다면 포워딩
	}
	
}
