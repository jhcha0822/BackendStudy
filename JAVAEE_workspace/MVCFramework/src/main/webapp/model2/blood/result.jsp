<%@page import="com.sds.mvcframework.blood.model.BloodManager"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%!
	
%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>당신의 혈액형에 대한 결과</h1>
	<!-- %String msg = (String)session.getAttribute("msg");% -->
	<%String msg = (String)request.getAttribute("msg");%>
	<%=msg%>
</body>
</html>