package com.sds.project0319.notice;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

// 글쓰기 요청을 처리하는 서블릿 클래스 정의
public class RegistServlet extends HttpServlet {
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "seshop";
	String pw = "1234";

	// 글쓰기 요청은 전달 데이터가 많기에 POST 형식으로 요청됨
	// 서블릿 부모가 가진 메서드인 doPost로 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // 예외처리 톰캣에 전가 -> 웹브라우저에 에러 출력
		// 응답정보 처리
		response.setContentType("text/html;charset=utf-8"); // 단축식 처리 가능
		// response.setCharacterEncoding("utf-8");
		
		// 인코딩 처리
		request.setCharacterEncoding("utf-8");

		// 클라이언트가 전송한 파라미터 3개 받기
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		PrintWriter out = response.getWriter(); // 응답 객체가 보유한 출력스트림 꺼내기
		out.print(title+" by "+writer+" about "+content+"<br>");

		// Oracle에 insert
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1) 드라이버 로드
		try {			
			out.print("드라이버 로드 성공");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, user, pw);
			if(con != null) {
				out.print("접속 성공");

				String sql = "insert into notice(notice_idx, title, writer,content)";
				sql += " values(seq_notice.nextval, ?, ?, ?)";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, writer);
				pstmt.setString(3, content);
				int result = pstmt.executeUpdate();
					out.print("<script>");
				if(result>0) {
					out.print("alert('등록 성공');");
					out.print("location.href='/notice/list';");
				}
				else {
					out.print("alert('등록 실패');");
					out.print("history.back();");
				}
					out.print("</script>");
			}
			else
				out.print("접속 실패");
		} catch(ClassNotFoundException e) {
			out.print("드라이버 로드 실패");
		} catch(SQLException e) {
			e.printStackTrace(); // 톰캣 서버 로그에 에러 원인 출력
		} finally {
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
}
