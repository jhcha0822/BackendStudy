package com.sds.project0319.notice;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

// JSP�� ���� �� �� �������� ����: ���������� ����� �� �ִ� ���
// ������ ������ ���� �ڹ� SE�ڵ带 �ۼ��ϱ⿡ �������� ǥ���� �� ��� ���� ���ڿ� ó���� �ؾ��ϱ⿡ ȿ������ ��������.
// ������(View)�� ���� �ڵ��� jsp�� �켼, �װ� �ƴ϶�� ���� �̿�


// ����: �ڹ� �ڵ� �� ���� ���������� ����� �� �ִ� Ŭ����
public class ListServlet extends HttpServlet {
	
	String user;

	// �����ֱ� �޼��� ��, �ʱ�ȭ �޼��带 �̿��Ͽ� web.xml���� �ѱ� �Ķ���������� ��������
	public void init(ServletConfig config) {
		user = config.getInitParameter("user");
		// tomcat�� �ܼ�(�͹̳�)�� ���, log���� ��µ�
		// System.out.println("�ʱ� �Ķ���� ����: "+user);
	}
	
	// ��� ��û�� ó���ϴ� ����: GET ���
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �Խ��� ��� ��û
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
		out.print("�ʱ� �Ķ���� ����: "+user);
		out.print("</body>");
		out.print("</html>");	
	}
}
