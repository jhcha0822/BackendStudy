<%@page import="com.sds.mvcframework.blood.model.BloodManager"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%!
	BloodManager manager = new BloodManager();
%>
<%
	// Model1: 디자인과 컨트롤러가 합쳐진 방식 
	// 이하 코드는 컨트롤러

	// 클라이언트가 요청한 혈액형 파라미터에 대해 분석하여 그 결과를 출력
	String blood = request.getParameter("blood");

	// 판단
	String msg = null;
	
	// 이미 정의해놓은 로직(모델) 객체 이용
	msg = manager.getAdvice(blood);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>당신의 혈액형에 대한 결과</h1>
	<%=msg%>
</body>
</html>