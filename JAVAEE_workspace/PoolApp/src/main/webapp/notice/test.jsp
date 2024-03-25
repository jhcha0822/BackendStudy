<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	// Tomcat이 지원하는 Connection Pool에 접근해보기
	
	// 아래의 DataSource가 Connection Pool을 구현한 구현체임
	// 객체에 접근하려면 sever.xml, context.xml의 외부 자원에 명시되어 있기에
	// 자바 코드를 이용해 xml에서 자원 검색을 시도해야 함
	Context context = null; // xml 등 외부 자원을 검색하는 객체
	DataSource ds = null; // 커넥션풀의 구현체
	
	context = new InitialContext();
	
	// jndi 이름을 통해 구현체를 얻기
	ds = (DataSource)context.lookup("java:comp/env/jndi/oracle"); // java:comp/env 까지는 고정
	
	Connection con = ds.getConnection(); // Pool로부터 커넥션 객체 하나를 꺼내기
	// 접속 발생이 아닌, 이미 접속된 객체를 얻어오는 것
	// 앞으로 빌려온 Connection을 통해 쿼리하고, 이를 끊지 말고 다시 반납
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = "select * from notice";
	
	pstmt = con.prepareStatement(sql);
	rs = pstmt.executeQuery();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%while(rs.next()) {%>
	<%=rs.getString("title") %><br>
	<%} %>
</body>
</html>
<%
	rs.close();
	pstmt.close();
	con.close(); // Tomcat의 Pool로부터 얻어온 Connection을 반납: close()
%>