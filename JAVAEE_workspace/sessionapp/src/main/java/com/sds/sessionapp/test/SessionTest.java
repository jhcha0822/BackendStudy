package com.sds.sessionapp.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 서블릿으로 요청을 처리하기 위해서는 개발자가 doXXX 재정의 필요
// 요청이 get방식: doGet(), post면 doPost()
public class SessionTest extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트의 브라우저에 출력할 문자열을 response 객체가 보유한 출력스트림에 모으자
		PrintWriter out = response.getWriter();
		// 한글 문자열이 깨지지 않으려면, jsp의 page 지시영역에서 작성한 기능을 똑같이 작성
		response.setContentType("text/html;charset=utf-8");
		out.print("처음 뵙겠습니다");
		
		// 클라이언트가 현재 서블릿을 요청 시 브라우저 메모리에 쿠키 id 없이 접근한다면 서버는 session 객체를 생성하고
		// 이 객체에 할당된 id를 응답정보를 넣을 시 쿠키값으로 추가한다
		// 개발자가 session 객체를 new하는 것이 아니라, 이미 생성되어진 session 객체를 얻어옴에 주의
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		System.out.println("현재 요청한 클라이언트에게 발급된 sessionId: "+sessionId);
		
		// 클라이언트는 아래의 사항에 해당하지만 않으면 세션 쿠키값이 유지된다.
		// 1) 웹브라우저 종료
		// 2) 정해진 시간동안 요청 없음
		
		// 서버가 발급한 세션 id인 쿠키가 유지되는 동안은 서버의 세션 객체에 접근할 수 있으므로,
		// 개발자가 세션 객체에 회원 정보를 넣어 둔다면: 세션이 유지되는 동안 회원정보도 계속 유지하여 보여줄 수 있다.
		
		// session 객체는 데이터를 Map으로 관리함 (java.util 패키지의 컬렉션프레임웍인 map을 상속받음)
		session.setAttribute("name", "KING");
		session.setAttribute("age", 31); // boxing을 통해 Integer로 변환
		
		// 클라이언트로 하여금 서버 측의 또 다른 jsp를 요청하도록 유도
		out.print("<script>");
		out.print("location.href='/test/mypage.jsp';");
		out.print("</script>");
	}
}
