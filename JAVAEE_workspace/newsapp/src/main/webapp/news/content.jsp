<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.sds.newsapp.news.News"%>
<%@page import="com.sds.newsapp.news.NewsDAO"%>
<%!
	NewsDAO newsDAO = new NewsDAO();
%>
<%
	String news_idx = request.getParameter("news_idx");
	News news = newsDAO.select(Integer.parseInt(news_idx));
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #04AA6D;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<%@ include file="../inc/head.jsp" %>
<!-- script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script -->
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<script type="text/javascript">
	$(function(){ // load 시
		$('#content').summernote();
		// WYCisWYG, 자동 html 편집 기능
		// 이미지 첨부가 가능하나, 바이너리로 변환되어 저장된다 (DB비용에서 손해)
		
		$("#bt_edit").click(function(){
			if(confirm("수정하시겠습니까?")){
				$("form").attr({
					action: "/news/edit", // servlet mapping
					method: "post"	
				});
				$("form").submit();
			}
		});
		
		$("#bt_del").click(function(){
			if(confirm("삭제하시겠습니까?")){
				// get 방식으로 요청
				location.href= "/news/delete?news_idx="+<%=news.getNews_idx()%>;
			}
		});
		
		$("#bt_list").click(function(){
			location.href = "/news/list.jsp";
		});
	});
</script>
</head>
<body>

	<h3>뉴스 상세보기</h3>

	<div class="container">
		<form action="/action_page.php">
			<input type="hidden" name="news_idx" value="<%=news.getNews_idx()%>">
			<input type="text" name="title" value="<%=news.getTitle()%>">
			<input type="text" name="writer" value="<%=news.getWriter()%>">
			<textarea id="content" name="content" style="height: 200px"><%=news.getContent()%></textarea>
			<input type="button" id="bt_edit" value="수정">
			<input type="button" id="bt_del" value="삭제">
			<input type="button" id="bt_list" value="목록">
		</form>
	</div>

</body>
</html>
