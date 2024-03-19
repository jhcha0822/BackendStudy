package com.sds.project0319.notice;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

// JSP나 서블릿 둘 다 사용목적은 동일: 서버측에서 실행될 수 있는 기술
// 하지만 서블릿은 순수 자바 SE코드를 작성하기에 디자인을 표현할 때 모든 것을 문자열 처리를 해야하기에 효율성이 떨어진다.
// 디자인(View)가 들어가는 코드라면 jsp가 우세, 그게 아니라면 서블랫 이용


// 서블릿: 자바 코드 중 오직 서버에서만 실행될 수 있는 클래스
public class ListServlet extends HttpServlet {
	
	String user;

	// 생명주기 메서드 중, 초기화 메서드를 이용하여 web.xml에서 넘긴 파라미터정보를 꺼내보자
	public void init(ServletConfig config) {
		user = config.getInitParameter("user");
		// tomcat의 콘솔(터미널)에 출력, log에도 출력됨
		// System.out.println("초기 파라미터 값은: "+user);
	}
	
	// 목록 요청을 처리하는 서블릿: GET 방식
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시판 목록 요청
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"UTF-8\">");
		out.print("<head>");
		out.print("<body>");
		out.print("<table width=\"100%\" border=\"1px\">");
		out.print("<tr>");
		out.print("<td>No</td");
		out.print("<td>Title</td");
		out.print("<td>Writer</td");
		out.print("<td>regdate</td");
		out.print("</tr>");
		out.print("</table>");
		out.print("초기 파라미터 값은: "+user);
		out.print("</body>");
		out.print("</html>");	
	}
}
