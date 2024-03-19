// JAVA 기술은 클래스 기반의 언어이기 때문에 어떤 플랫폼을 개발하던
// 모든 코드는 .java라는 클래스에 작성해야 한다
// jsp는 javaEE에서만 지원하는 서버측 스크립트 기술이다.
package com.sds.project0319.test;

import javax.servlet.http.HttpServlet; // jsp 대신 서버에서 요청을 처리하는 서블릿 클래스
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest; // 요청 객체
import javax.servlet.http.HttpServletResponse; // 응답 객체
import java.io.PrintWriter;

// 이 클래스는 로컬 PC가 아니라 서버측에서만 가동될 수 있는 클래스이며
// 이러한 목적의 javaEE 기반의 클래스를 가리켜 servlet이라 한다
public class MyServlet extends HttpServlet {
	// 개발자는 웹 서버에서 실행되려는 목적의 클래스를 컴파일 한 후
	// WEB-INF/classes에 올려두기만 하면 됨
	// 웹 브라우저인 클라이언트들이 웹 서버에 요청을 시도할 때 마다
	// 실행되는 구조이기 때문에 main()메서드를 가질 필요가 없다
	
	// 클라이언트인 웹 브라우저가 요청을 시도할 때 동작
	// jsp가 하던 역할을 그대로 수행한다

	// 아래의 메서드는 HttpServlet이 보유한 doXXX 메서드 중 하나로써
	// 클라이언트가 GET 방식으로 요청을 시도하면 동작하는 메서드이다
	// 우측의 throws는 try~catch를 개발자가 처리하고 싶지 않을 때
	// 메서드 호출자에게 예외처리를 전가시키는 방법이다
	// 또한 아래의 메서드는 클라이언트의 요청을 처리해야 하므로
	// jsp에서의 request와 response가 아래의 메서드에서도 동일하게 필요하다
	// 매개변수로 포함시켜준다
	// jsp에서 request 내장객체의 자료형: HttpServletRequest
	// jsp에서 response 내장객체의 자료형: HttpServletResponse
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트 웹브라우저에 출력할 메시지 등록
		// jsp였다면 out.print();에 메시지 누적
		// 서블릿은 클래스기반으로 개발자가 일일이 코드를 작성해야 함; 내장객체 지원안됨

		// jsp에서의 page 지시영역에서 명시된 contentType과 charset을 여기서도 진행
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		// 응답객체가 보유한 출력 스트림을 얻기
		PrintWriter out = response.getWriter();
		// 응답객체가 보유한 출력 스트림에 문자열을 누적시켜두기
		out.print("My First Servlet<br>");
	}	

	// 이 메서드는 클라이언트가 post 방식으로 요청을 시도할 때 동작함
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// jsp에서의 page 지시영역에서 명시된 contentType과 charset을 여기서도 진행
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		// 한글이 깨지지 않게 설정
		request.setCharacterEncoding("utf-8");
		// 클라이언트가 전송한 파라미터를 얻어보자
		String title = request.getParameter("title");

		// 응답객체가 보유한 출력 스트림을 얻기
		PrintWriter out = response.getWriter();
		// 응답객체가 보유한 출력 스트림에 문자열을 누적시켜두기
		out.print("Title is: "+title); // 출력이 발생하는 것이 아니라 response 객체가 보유한 출력 스트림에
   									   // 추후 tomcat이 생성할 html 컨텐츠에 문자열을 모으는 행위
	}
}
