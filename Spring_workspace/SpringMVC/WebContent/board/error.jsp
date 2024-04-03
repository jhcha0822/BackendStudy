<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Error</h1>
	<p>이용에 불편을 드려 죄송합니다</p>
	<p><%= request.getAttribute("msg") %></p>
	<p><a href="/board/list">목록 바로가기</a></p>
</body>
</html>