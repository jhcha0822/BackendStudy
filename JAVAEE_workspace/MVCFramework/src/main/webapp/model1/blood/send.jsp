<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function send(){
		let form = document.querySelector("form");
		form.action = "/model1/blood/result.jsp";
	}
</script>
</head>
<body>
	<pre> <!-- 줄바꿈 및 들여쓰기를 디자인적으로 인정해주는 태그 -->
		<form>
			<select name="blood">
				<option value="">혈액형 선택</option>
				<option value="A">A형</option>
				<option value="B">B형</option>
				<option value="AB">AB형</option>
				<option value="O">O형</option>
			</select>
			<br>
			<button type="button"  onClick="send()">전송</button>
		</form>
	</pre>
</body>
</html>