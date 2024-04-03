<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<%@ include file='/inc/header.jsp'%>
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

		$("button").click(function() {
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
