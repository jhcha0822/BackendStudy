<%@page import="com.sds.spring.domain.Board"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Board board = (Board)request.getAttribute("board");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../inc/header_link.jsp" %>
<script type="text/javascript">
	
	function edit() {
		$("form").attr({
			action : "/board/update",
			method : "post"
		});
		$("form").submit();
	}
	
	function del() {
		$("form").attr({
			action : "/board/delete",
			method : "post"
		});
		$("form").submit();
	}
	
	$(document).ready(function() {
		$("textarea").summernote({
			height : 300,
			placeholder : "내용 입력"
		}); // textarea를 summernote 적용 
	
		$("#bt_edit").click(()=>{
			if(confirm("수정하시겠습니까?"))
				edit();
		});
		
		$("#bt_del").click(()=>{
			if(confirm("삭제하시겠습니까?"))
				del();
		});
				
		$("#bt_list").click(()=>{
			location.href="/board/list";
		});
	});
</script>
</head>
<body>
	<div class="container">
		<h2>Stacked form</h2>
		<form>
			<input type="hidden" name="board_idx" value="<%= board.getBoard_idx() %>">
			<div class="form-group">
				<input type="text" class="form-control" value="<%= board.getTitle() %>"
					name="title">
			</div>
			<div class="form-group">
				<input type="text" class="form-control" value="<%= board.getWriter() %>"
					name="writer">
			</div>
			<div class="form-group">
				<textarea class="form-control" name="content"><%= board.getContent() %></textarea>
			</div>
			<button type="button" class="btn btn-primary" id="bt_edit">수정</button>
			<button type="button" class="btn btn-primary" id="bt_del">삭제</button>
			<button type="button" class="btn btn-primary" id="bt_list">목록</button>
		</form>
	</div>
</body>
</html>