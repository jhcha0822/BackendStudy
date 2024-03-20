package com.sds.sessionapp.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sds.sessionapp.common.EncryptionManager;

public class LoginServlet extends HttpServlet{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="seshop";
	String password="1234";
	
	//클라이언트가 전송한 데이터가, 비밀번호가 포함되어 있기 때문에 post 방식으로 요청
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8"); //jsp의 page 지시영역에 해당 
		PrintWriter out = response.getWriter(); //html 문서를 구성하기 위한 출력스트림 
		
		//클라이언트가 전송한 파라미터 받기
		String id=request.getParameter("id");
		String pass= EncryptionManager.getHashFromText(request.getParameter("pass")) ;
		
		System.out.println("아이디는 "+id);
		System.out.println("패스워드는 "+pass);
		
		//넘겨받은 계정 정보가 오라클이 있다면..회원인증을 처리, 세션에 저장해놓고  이 회원이 브라우저를
		//꺼버리지 않는 동안은 데이터를 유지해주자!!
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			Class.forName(driver); //드라이버 로드 
			con = DriverManager.getConnection(url, user, password);
			
			if(con==null) 
				System.out.println("접속 실패");
			else {
				//회원 조회 쿼리 실행 
				String sql="select * from member where id=? and pass=?";
				pstmt = con.prepareStatement(sql); //쿼리 객체 생성
				pstmt.setString(1, id); //사용자 id 파라미터값
				pstmt.setString(2, pass); //사용자 pass 파라미터 값
				
				//쿼리 실행 후 결과 ResultSet 받기 
				rs = pstmt.executeQuery(); 
				
				//만일 회원이 존재한다면 rs.next() true가 반환됨 
				if(rs.next()) {
					//이 회원만의 서비스를 제공: 서버가 이 회원을 계속 기억하는 것처럼
					//세션객체 얻기 
					HttpSession session=request.getSession(); //이 요청과 관련된 세션
					//session.setAttribute(sql, session); //회원 정보를 담는다...
					
					// 세션 객체에 회원 정보를 낱개로 파편화하여 담지 않고,
					// 객체지향적으로 처리: 클래스의 인스턴스를 담아보기
					// 클래스 중, 로직이 아닌 데이터만을 담기위한 클래스를 가리켜 DTO라고 함
					// Data Transfer Object
					
					// 실제 데이터는 현재 rs에 담겨있음
					// rs의 커서를 한칸 전진시켜 보유한 데이터를 DTO 인스턴스 한개를 생성해 setter메서드로 값 입력
					Member member = new Member(); // empty 상태의 DTO
					member.setId(rs.getString("id"));
					member.setPass(rs.getString("pass"));
					member.setName(rs.getString("name"));
					member.setEmail(rs.getString("email"));
					member.setReceive(rs.getInt("receive"));
					member.setRegdate(rs.getString("regdate"));
					
					// 다 채워진 한 사람에 대한 정보를 세션 객체에 담아놓고 회원서비스를 제공하자
					session.setAttribute("member", member);

					out.print("<script>");
					out.print("alert('로그인 성공');");
					out.print("location.href='/member/mypage.jsp';");
					out.print("</script>");
				}
				else {
					out.print("<script>");
					out.print("alert('로그인 정보가 올바르지 않습니다');");
					out.print("history.back();");
					out.print("</script>");
				}				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}