<%@page import="com.sds.sessionapp.member.Member"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background:yellow">
	<%
		// jsp에서는 세션을 내장객체로 지원한다.
		// 명칭 역시 session으로 정해져 있다
		Member member = (Member)session.getAttribute("member");
	%>
	<h1><%=member.getName()%>님의 정보</h1>
	<p>
		아이디: <%=member.getId()%><br>
		이름: <%=member.getName()%><br>
		이메일: <%=member.getEmail()%><br>
		수신동의: <%=member.getReceive()%><br>
		가입일: <%=member.getRegdate()%><br>
	</p>
</body>
</html>