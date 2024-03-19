package com.sds.project0319.notice;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

// �۾��� ��û�� ó���ϴ� ���� Ŭ���� ����
public class RegistServlet extends HttpServlet {
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "seshop";
	String pw = "1234";

	// �۾��� ��û�� ���� �����Ͱ� ���⿡ POST �������� ��û��
	// ���� �θ� ���� �޼����� doPost�� ó��
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // ����ó�� ��Ĺ�� ���� -> ���������� ���� ���
		// �������� ó��
		response.setContentType("text/html;charset=utf-8"); // ����� ó�� ����
		// response.setCharacterEncoding("utf-8");
		
		// ���ڵ� ó��
		request.setCharacterEncoding("utf-8");

		// Ŭ���̾�Ʈ�� ������ �Ķ���� 3�� �ޱ�
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		PrintWriter out = response.getWriter(); // ���� ��ü�� ������ ��½�Ʈ�� ������
		out.print(title+" by "+writer+" about "+content+"<br>");

		// Oracle�� insert
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1) ����̹� �ε�
		try {			
			out.print("����̹� �ε� ����");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, user, pw);
			if(con != null) {
				out.print("���� ����");

				String sql = "insert into notice(notice_idx, title, writer,content)";
				sql += " values(seq_notice.nextval, ?, ?, ?)";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, writer);
				pstmt.setString(3, content);
				int result = pstmt.executeUpdate();
					out.print("<script>");
				if(result>0) {
					out.print("alert('��� ����');");
					out.print("location.href='/notice/list';");
				}
				else {
					out.print("alert('��� ����');");
					out.print("history.back();");
				}
					out.print("</script>");
			}
			else
				out.print("���� ����");
		} catch(ClassNotFoundException e) {
			out.print("����̹� �ε� ����");
		} catch(SQLException e) {
			e.printStackTrace(); // ��Ĺ ���� �α׿� ���� ���� ���
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
