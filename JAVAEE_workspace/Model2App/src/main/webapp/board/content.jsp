<%@page import="com.sds.model2app.domain.Board"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	// 컨트롤러 결과 가져오기
	Board board = (Board)request.getAttribute("board");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<%@ include file='/inc/header.jsp'%>
<script type="text/javascript">
	
	function edit() {
		$("form").attr({
			action : "/board/edit.do",
			method : "post"
		});
		$("form").submit();
	}
	
	function del() {
		$("form").attr({
			action : "/board/delete.do",
			method : "post"
		});
		$("form").submit();
	}

	$(document).ready(function() {
		$("textarea").summernote({
			height : 300,
			placeholder : "내용 입력"
		}); // textarea를 summernote 적용 

		$("#bt_edit").click(function() {
			if(confirm("수정하시겠습니까?"))
				edit();
		});
		
		$("#bt_del").click(function() {
			if(confirm("삭제하시겠습니까?"))
				del();
		});
		
		$("#bt_list").click(function() {
			location.href="/board/list.do"
		});
	});
	
</script>
</head>
<body>

	<div class="container">
		<h2>Stacked form</h2>
		<form>
		
			<input type="hidden" name="board_idx" value="<%=board.getBoard_idx() %>">
			
			<div class="form-group">
				<input type="text" class="form-control" value="<%=board.getTitle()%>"
					name="title">
			</div>
			<div class="form-group">
				<input type="text" class="form-control" value="<%=board.getWriter()%>"
					name="writer">
			</div>
			<div class="form-group">
				<textarea class="form-control" name="content"><%=board.getContent() %></textarea>
			</div>
			<button type="button" class="btn btn-primary" id="bt_edit">수정</button>
			<button type="button" class="btn btn-primary" id="bt_del">삭제</button>
			<button type="button" class="btn btn-primary" id="bt_list">목록</button>
		</form>
	</div>

</body>
</html>
