package com.sds.newsapp.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistServlet extends HttpServlet { // 서버에서 실행 가능하게 서블릿으로 정의
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "seshop";
	String pw = "1234";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		

		// out 스트림 객체
		response.setContentType("text/html;charset=utf-8"); // 먼저 인코딩 수행
		PrintWriter out = response.getWriter();
		
		// 한글 인코딩 설정 (스트림 생성 전)
		request.setCharacterEncoding("utf-8");
		
		// 클라이언트가 전송한 파라미터 받기
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			// System.out.println("드라이버 로드");
			
			con = DriverManager.getConnection(url, user, pw);
			
			StringBuffer sb = new StringBuffer();
			
			if(con != null) {
				// 오라클에서 바인드 변수를 사용하기 위해서 :변수명 을 사용하지만,
				// jdbc 코드에서는 ? 로 처리
				// 바인드 변수의 목적은 db의 성능 개선 및 향상
				sb.append("insert into news(news_idx, title, writer, content)");
				sb.append(" values(seq_news.nextval, ?, ?, ?)");
				
				pstmt = con.prepareStatement(sb.toString());
				pstmt.setString(1, title);
				pstmt.setString(2, writer);
				pstmt.setString(3, content);
				
				int result = pstmt.executeUpdate(); // 쿼리 실행
				out.print("<script>");
				if(result>0) {
					out.print("alert('입력 성공');");
					out.print("location.href='/news/list.jsp';");
				}
				else {
					out.print("alert('입력 실패');");
					out.print("history.back();");
				}
				out.print("</script>");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
