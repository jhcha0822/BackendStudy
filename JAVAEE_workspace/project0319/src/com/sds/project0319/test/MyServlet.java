// JAVA ����� Ŭ���� ����� ����̱� ������ � �÷����� �����ϴ�
// ��� �ڵ�� .java��� Ŭ������ �ۼ��ؾ� �Ѵ�
// jsp�� javaEE������ �����ϴ� ������ ��ũ��Ʈ ����̴�.
package com.sds.project0319.test;

import javax.servlet.http.HttpServlet; // jsp ��� �������� ��û�� ó���ϴ� ���� Ŭ����
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest; // ��û ��ü
import javax.servlet.http.HttpServletResponse; // ���� ��ü
import java.io.PrintWriter;

// �� Ŭ������ ���� PC�� �ƴ϶� ������������ ������ �� �ִ� Ŭ�����̸�
// �̷��� ������ javaEE ����� Ŭ������ ������ servlet�̶� �Ѵ�
public class MyServlet extends HttpServlet {
	// �����ڴ� �� �������� ����Ƿ��� ������ Ŭ������ ������ �� ��
	// WEB-INF/classes�� �÷��α⸸ �ϸ� ��
	// �� �������� Ŭ���̾�Ʈ���� �� ������ ��û�� �õ��� �� ����
	// ����Ǵ� �����̱� ������ main()�޼��带 ���� �ʿ䰡 ����
	
	// Ŭ���̾�Ʈ�� �� �������� ��û�� �õ��� �� ����
	// jsp�� �ϴ� ������ �״�� �����Ѵ�

	// �Ʒ��� �޼���� HttpServlet�� ������ doXXX �޼��� �� �ϳ��ν�
	// Ŭ���̾�Ʈ�� GET ������� ��û�� �õ��ϸ� �����ϴ� �޼����̴�
	// ������ throws�� try~catch�� �����ڰ� ó���ϰ� ���� ���� ��
	// �޼��� ȣ���ڿ��� ����ó���� ������Ű�� ����̴�
	// ���� �Ʒ��� �޼���� Ŭ���̾�Ʈ�� ��û�� ó���ؾ� �ϹǷ�
	// jsp������ request�� response�� �Ʒ��� �޼��忡���� �����ϰ� �ʿ��ϴ�
	// �Ű������� ���Խ����ش�
	// jsp���� request ���尴ü�� �ڷ���: HttpServletRequest
	// jsp���� response ���尴ü�� �ڷ���: HttpServletResponse
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Ŭ���̾�Ʈ ���������� ����� �޽��� ���
		// jsp���ٸ� out.print();�� �޽��� ����
		// ������ Ŭ����������� �����ڰ� ������ �ڵ带 �ۼ��ؾ� ��; ���尴ü �����ȵ�

		// jsp������ page ���ÿ������� ��õ� contentType�� charset�� ���⼭�� ����
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		// ���䰴ü�� ������ ��� ��Ʈ���� ���
		PrintWriter out = response.getWriter();
		// ���䰴ü�� ������ ��� ��Ʈ���� ���ڿ��� �������ѵα�
		out.print("My First Servlet<br>");
	}	

	// �� �޼���� Ŭ���̾�Ʈ�� post ������� ��û�� �õ��� �� ������
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// jsp������ page ���ÿ������� ��õ� contentType�� charset�� ���⼭�� ����
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		// �ѱ��� ������ �ʰ� ����
		request.setCharacterEncoding("utf-8");
		// Ŭ���̾�Ʈ�� ������ �Ķ���͸� ����
		String title = request.getParameter("title");

		// ���䰴ü�� ������ ��� ��Ʈ���� ���
		PrintWriter out = response.getWriter();
		// ���䰴ü�� ������ ��� ��Ʈ���� ���ڿ��� �������ѵα�
		out.print("Title is: "+title); // ����� �߻��ϴ� ���� �ƴ϶� response ��ü�� ������ ��� ��Ʈ����
   									   // ���� tomcat�� ������ html �������� ���ڿ��� ������ ����
	}
}
