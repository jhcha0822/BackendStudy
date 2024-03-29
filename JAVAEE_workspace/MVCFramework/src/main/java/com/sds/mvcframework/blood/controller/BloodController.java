package com.sds.mvcframework.blood.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcframework.blood.model.BloodManager;
import com.sds.mvcframework.controller.Controller;

// MVC 패턴(개발방법론)을 JavaEE로 구현한 개발 방식을 Model2 방식이라 함

// Model2 개발벙법
// 1) M: 순수 자바 클래스(POJO)
// 2) V: JSP
// 3) C: 클라이언트 요청을 받을 수 있고 + 웹서버에서 실행될 수 있으며 + 자바의 기술을 이해
//       jsp와 servlet이 존재
//       jsp도 가능하나 이미 view에 사용되고 있음

public class BloodController implements Controller{ // extends HttpServlet{
	
	BloodManager manager = new BloodManager();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String blood = request.getParameter("blood");
		String msg = null;
		msg = manager.getAdvice(blood);
		
		// 현재 서블릿이 out.print로 결과를 보여주는 것도 가능하나
		// 이는 컨트롤러로서의 서블릿이 디자인도 담당하게 되므로 Model2 원칙에 위배됨 (Model1)
		// 결과는 jsp 파일에서 보여주어야 함: Controller와 view는 철저히 나누어 개발해야 함
		
		// msg 변수를 result.jsp로 전달해주어야 함
		// 서블릿이 또 다른 서블릿인 jsp에게 직접적으로 데이터를 전달하는 방법은 application 내장 객체(서버를 닫아야 함), 세션 또는 ()를 이용하지 않으면 불가능
		
		// 1) session 이용
		// 지역변수 msg를 세션에 저장
		// HttpSession session = request.getSession(); // 세션은 일정시간 방문하지 않거나 브라우저를 닫지 않으면 요청이 끊긴 후 재접속 시에도 계속 사용 가능
		// 세션은 java collection framework의 map을 상속받음 (key-value)
		// session.setAttribute("msg", msg);
	
		// 2) application 내장 객체 이용: session보다 scope 범위가 넓다
		// ServletContext application = request.getServletContext();
		// application.setAttribute("msg", msg);
		
		// 3) 포워딩
		// 현재 요청에 대해 응답을 바로 하지 말고, 서버상에 존재하는 또 다른 서블릿 or jsp에게 요청을 전달해보자
		// 응답을 하지 않는 동안엔 request, response는 죽지 않으므로, 전달할 데이터가 있다면 request에게 넣자
		request.setAttribute("msg", msg);
		
		// 포워딩을 위한 객체 RequestDispatcher
		RequestDispatcher dis = request.getRequestDispatcher("/model2/blood/result.jsp"); // 전달할 주소
		dis.forward(request, response); // 포워딩 
		// 주소가 그대로
		
		// 결과를 보여주기: View 파일 결정
		// response.sendRedirect("/model2/blood/result.jsp"); // location.href=""로 응답정보를 보내는 것과 동일: 지정한 url로 재접속
	}
		
}
