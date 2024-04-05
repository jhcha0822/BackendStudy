<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../inc/header_link.jsp" %>
<script type="text/javascript">
	
	function regist() {
		$("form").attr({
			action : "/board/regist",
			method : "post"
		});
		$("form").submit();
	}
	
	$(document).ready(function() {
		$("textarea").summernote({
			height : 300,
			placeholder : "내용 입력"
		}); // textarea를 summernote 적용 
	
		$("button").click(()=> {
			regist();
		});
	});
	
</script>
</head>
<body>
	<div class="container">
		<h2>Stacked form</h2>
		<form>
			<div class="form-group">
				<input type="text" class="form-control" placeholder="제목"
					name="title">
			</div>
			<div class="form-group">
				<input type="text" class="form-control" placeholder="글쓴이"
					name="writer">
			</div>
			<div class="form-group">
				<textarea class="form-control" name="content"></textarea>
			</div>
			<button type="button" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>