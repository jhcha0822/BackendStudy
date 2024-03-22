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

	// 이 함수는 최초의 onLoad시에도 호출되어야 하고
	// 댓글 등록시에도 비동기방식으로 목록을 가져와야 함
	// 따라서 ajax로 새로고침 없이 갱신 처리한다
	function getCommentsList() {
		let xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function(){
			if(this.readyState==4 && this.status==200) {
				// console.log(this.responseText);
				// JSON은 객체가 아닌 단순 문자열이기에 배열의 길이등을 받아올 수 없다
				// 따라서 넘어온 문자열을 분석하여 객체화시키는 JSON 내장객체를 받아온다
				let obj = JSON.parse(this.responseText);
				// console.log("게시물 수는: ", obj.commentsList.length);

				//obj.commentList만큼, 테이블 태그를 동적으로 출력해보자
				let tag="<table width=\"100%\" border=\"1px\">";
				
				tag+="<tr>";
				tag+="<th>댓글 메시지</th>";
				tag+="<th>작성자</th>";
				tag+="<th>등록일</th>";
				tag+="</tr>";
				
				for(let i=0; i<obj.commentsList.length; i++) {
					let json = obj.commentsList[i];
					tag+="<tr>";
					tag+="<td>"+json.msg+"</td>";
					tag+="<td>"+json.cwriter+"</td>";
					tag+="<td>"+json.cregdate+"</td>";
					tag+="</tr>";
				}
				
				tag+="</table>";
				
				$("#comments_container").html(tag);
			}
		}
		xhttp.open("GET", "/comments/list?news_idx=<%=news_idx%>");
		xhttp.send();
	}

	$(function(){ // load 시
		$('#content').summernote();
		// WYCisWYG, 자동 html 편집 기능
		// 이미지 첨부가 가능하나, 바이너리로 변환되어 저장된다 (DB비용에서 손해)
		
		$("#bt_edit").click(function(){
			if(confirm("수정하시겠습니까?")){
				$("#form1").attr({
					action: "/news/edit", // servlet mapping
					method: "post"	
				});
				$("#form1").submit();
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
		
		$("#bt_reply").click(function(){
			// 비동기 통신으로 요청
			let xhttp = new XMLHttpRequest(); // 비동기 백그라운드 통신 객체 생성
			
			// 서버에 응답이 성공적으로 들어오는지 체크
			xhttp.onreadystatechange=function(){
				if(this.readyState==4 && this.status==200){ // 요청이 도달 && 서버에서 요청을 정상적으로 처리
					// alert(this.responseText);
					getCommentsList(); // 목록을 비동기 방식으로 갱신
				}
			}
			
			// 통신 전 헤더 구성하기
			xhttp.open("POST", "/comments/regist");
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); // form의 역할
			
			let msg = $("#msg").val();  //메시지 값
			let cwriter = $("#cwriter").val(); //작성자 값
			let news_idx = <%=news_idx%>;
			
			xhttp.send("msg="+msg+"&cwriter="+cwriter+"&news_idx="+news_idx);
			
			// 기사의 댓글 목록 가져오기
			getCommentsList();
			
		});
	});
</script>
</head>
<body>

	<h3>뉴스 상세보기</h3>

	<div class="container">
		<form action="/action_page.php" id="form1">
			<input type="hidden" name="news_idx" value="<%=news.getNews_idx()%>">
			<input type="text" name="title" value="<%=news.getTitle()%>">
			<input type="text" name="writer" value="<%=news.getWriter()%>">
			<textarea id="content" name="content" placeholder="내용 입력" style="height: 400px"><%=news.getContent()%></textarea>
			<input type="button" id="bt_edit" value="수정">
			<input type="button" id="bt_del" value="삭제">
			<input type="button" id="bt_list" value="목록">
		</form>
	</div>
	<!-- 댓글 등록 폼 -->
	<div class="container">
		<form id="form2">
			<input type="text" id="msg" style="width:70%" placeholder="댓글 입력">
			<input type="text" id="cwriter" style="width:15%" placeholder="작성자">
			<input type="button" value="등록" id="bt_reply" style="width:10%">		
		</form>
	</div>
	<!-- 댓글 목록 -->
	<div class="container" id="comments_container"><!-- innerHTML로 동적 테이블 생성 --></div>
</body>
</html>
