<%@page import="com.sds.newsapp.news.News"%>
<%@ page import="com.sds.newsapp.news.NewsDAO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! 
	// list.jsp가 서블릿으로 변경되어 질 때의 멤버영역
	// 선언부에서 has-a 로 보유하자
	NewsDAO newsDAO = new NewsDAO();
%>
<%
	// 이전까지는 여기서 DB와 연동하였으나,
	// 이제 DAO에게 맡기고 결과를 받아오자
	// 서비스 메서드 영역에서 로직 작성
	List<News> list = newsDAO.selectAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
<%@ include file="../inc/head.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(function(){
		// 버튼에 이벤트 연결
		$("button").click(function(){
			$(location).attr("href", "/news/write.jsp"); // location.href="/news/write.jsp"; 와 같은 기능
		});
	});
</script>
</head>
<body>

	<h2>뉴스 게시판</h2>
	
	<table>
		<tr>
			<th>No</th>
			<th>뉴스 제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<% for(int i=0; i<list.size(); i++) { %>
		<% News news = list.get(i); %>
		<tr>
			<td><%=news.getNews_idx()%></td>
			<td>
				<a href="/news/content.jsp?news_idx=<%=news.getNews_idx()%>">
				<%=news.getTitle()%><%if(news.getCnt()>0){%>[<%=news.getCnt()%>]<%}%></a>
			</td>
			<td><%=news.getWriter()%></td>
			<td><%=news.getRegdate()%></td>
			<td><%=news.getHit()%></td>
		</tr>
		<% } %>
		<tr>
			<td colspan="5">
				<button>글쓰기</button>
			</td>
		</tr>
	</table>

</body>
</html>
