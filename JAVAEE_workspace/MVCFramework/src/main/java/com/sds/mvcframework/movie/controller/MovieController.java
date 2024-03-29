package com.sds.mvcframework.movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcframework.controller.Controller;
import com.sds.mvcframework.movie.model.MovieManager;

// 영화에 대한 판단 요청 처리
// 즉 Controller
// jsp로도 컨트롤러 정의가 가능하나
// 자바EE의 model2 방식을 준수하기 위해 서블릿으로 작성
public class MovieController implements Controller { // 서블릿일 필요가 없어졌다 extends HttpServlet{
	
	MovieManager manager = new MovieManager();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩 설정: 한글이 안깨지게
		request.setCharacterEncoding("utf-8");
		
		// 파라미터 받기
		String movie = request.getParameter("movie"); // 3) 로직 수행
		
		// 컨트롤러는 직접 일하지 않음. 모델영역인 logic의 재사용성을 염두해두어야 하기 때문
		String msg = manager.getAdvice(movie); // 4) 결과 저장
		
		// 결과는 view에서 보여주기
		// msg는 또 다른 서블릿 jsp에게 전달되어야 하므로
		// 현재 서블릿이 아닌 view로 포워딩
		request.setAttribute("msg", msg);
		RequestDispatcher dis = request.getRequestDispatcher("/model2/movie/result.jsp"); // 5) 결과 페이지
		dis.forward(request, response); // 기존의 객체 유지
	}
}
