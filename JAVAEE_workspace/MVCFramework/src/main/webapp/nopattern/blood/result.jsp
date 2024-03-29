<%@ page contentType="text/html;charset=UTF-8" %>
<%
	// 클라이언트가 요청한 혈액형 파라미터에 대해 분석하여 그 결과를 출력
	String blood = request.getParameter("blood");

	// 판단
	String msg = null;
	
	if(blood.equals("A")){
		msg = "소심함";
	}
	else if(blood.equals("B")){
		msg = "고집있음";
	}
	else if(blood.equals("AB")){
		msg = "귀가 얇음";
	}
	else if(blood.equals("O")){
		msg = "가벼움";
	}
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