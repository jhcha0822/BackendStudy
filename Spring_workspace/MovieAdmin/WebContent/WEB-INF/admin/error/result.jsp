<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="background: aliceblue">
	<h1>이용에 불편을 드려 죄송합니다</h1>
	<h3>에러 발생</h3>
	<p>
		<%
			if(request.getAttribute("e") != null) {
				RuntimeException e = (RuntimeException)request.getAttribute("e");
				out.print(e.getMessage()); // error의 e 객체가 아닌 msg 만을 꺼내기
			}
		%>
	</p>
	<a href="/admin">관리자 메인 페이지</a>
</body>
</html>