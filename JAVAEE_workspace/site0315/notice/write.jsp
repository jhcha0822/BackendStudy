<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

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

input[type=submit] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script>
    // 자바스크립트는 프론트엔드단에서 동작: 소스가 노출됨
    // 따라서 데이터베이스 연동을 직접 하게 되면 보안상 위협
    // 직접 오라클에 연동하지 말고 데이터를 서버로 전송한 뒤,
    // 서버측의 스크립트 기술인 jsp에게 연동을 요청하자
    function send() {
        // 폼 양식을 서버측 페이지인 regist.jsp에 전송
        let form1 = document.getElementById("form1");
        form1.action="/notice/regist.jsp"; // 대상
        form1.method="post"; // 서버에 요청시 전송할 데이터가 있다면 post방식으로 요청
        form1.submit(); // 전송
    }
</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form id="form1">
    <!-- id & name -->
    <!-- 공통점: 문서 내의 요소를 구분하기 위함 -->
    <!-- 차이점: name은 중복을 허용하고 서버에 전송 시 전송 파라미터 역할도 수행 -->
    <!-- 즉, http 상의 전송 시  -->
    <input type="text" name="title" placeholder="제목 입력">
    <input type="text" name="writer" placeholder="작성자 입력">
    <textarea name="content" placeholder="내용 입력" style="height:200px"></textarea>
    <input type="button" value="글쓰기" onClick="send()">
  </form>
</div>

</body>
</html>
