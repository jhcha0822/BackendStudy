<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%!
    // jsp 페이지의 멤버영역
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String user = "seshop";
    String pass = "1234";
%>
<%
    // 클라이언트가 전송한 파라미터를 꺼내보자
    // 클라이언트는 항상 "" 사이의 문자열로 전송함
    String notice_idx = request.getParameter("notice_idx");
    String sql = "select * from notice where notice_idx="+notice_idx;

    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    con = DriverManager.getConnection(url, user, pass);
    if(con != null) {
        pstmt = con.prepareStatement(sql); // 쿼리수행 객체
        rs = pstmt.executeQuery();
        rs.next();
    }
%>
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

    // 자바스크립트는 db연동을 직접 수행할 수 없기에 서버측에 삭제를 요청하자
    function del() {
        // 서버쪽 삭제를 담당하는 jsp에게 요청을 시도
        location.href="/notice/del.jsp?notice_idx="+<%notice_idx%>;
    }
</script>
</head>
<body>

<h3>글 상세보기</h3>

<div class="container">
  <form id="form1">
    <!-- id & name -->
    <!-- 공통점: 문서 내의 요소를 구분하기 위함 -->
    <!-- 차이점: name은 중복을 허용하고 서버에 전송 시 전송 파라미터 역할도 수행 -->
    <!-- 즉, http 상의 전송 시  -->
    <input type="text" name="title" value="<%=rs.getString("title")%>">
    <input type="text" name="writer" value="<%=rs.getString("writer")%>">
    <textarea name="content" style="height:200px"><%=rs.getString("content")%></textarea>
    <input type="button" value="수정" onClick="">
    <input type="button" value="삭제" onClick="del();">
    <input type="button" value="목록" onClick="location.href='/notice/list.jsp'">
  </form>
</div>

</body>
</html>
<%
  if(rs != null)
    rs.close();
  if(pstmt != null)
    pstmt.close();
  if(con != null)
    con.close();
%>