<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%
  // 클라이언트가 전송한 gallery_idx를 추출
  String gallery_idx = request.getParameter("gallery_idx");
  String sql = "select * from gallery where gallery_idx="+gallery_idx;
  // out.print(sql);

  String driver = "oracle.jdbc.driver.OracleDriver";
  String url = "jdbc:oracle:thin:@localhost:1521:XE";
  String user = "seshop";
  String pw = "1234";

  Class.forName(driver);
  Connection con = null;
  PreparedStatement pstmt = null;
  ResultSet rs;
  
  con = DriverManager.getConnection(url, user, pw);
  pstmt = con.prepareStatement(sql);
  rs = pstmt.executeQuery();

  rs.next();
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
<script>
  function edit() {
    if(confirm("수정하시겠습니까?")) { // 실수방지
      form1 = document.getElementById("form1");
			form1.action="/gallery/update.jsp"; //서버측의 업로드 요청을 받을 url
			form1.method="post";
			form1.submit(); //전송
    }
  }
  

  function delete() {
    if(confirm("삭제하시겠습니까?")) {
      // location.href="/gallery/delete.jsp?gallery_idx=<%=gallery_idx%>";
      
      // 파일명과 gallery_idx 두개 이상을 전송해야 하므로
      // 아래쪽 form태그에 히든으로 파라미터들이 준비되어 있기에 기존 form을 전송
      // 삭제 요청시엔 파일을 전송하는 것이 아니므로 복합 데이터 타입을 보낼 필요가 없다
      form1 = document.getElementById("form1");

      // encoding 타입 변경
      form1.encoding=""; // js의 syntax
      form1.method="post";
      form1.action="/gallery/delete.jsp";
      form1.submit();
    }
  }
</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <%-- 복합 데이터 명시 --%>
  <form id="form1" enctype="multipart/form-data"> 
    <input type="hidden" name="filename" value="<%=rs.getString("filename")%>">
    <input type="hidden" name="gallery_idx" value="<%=rs.getString("gallery_idx")%>">
    <input type="text" name="title" value="<%=rs.getString("title")%>">
    <input type="text" name="writer" value="<%=rs.getString("writer")%>">
    <textarea id="subject" name="content" style="height:200px"><%=rs.getString("content")%></textarea>
    <p>
      <img src="/data/<%=rs.getString("filename")%>" width="200px">
    </p>
    <input type="file" name="photo">
    <p></p>
    <input type="button" value="수정" onClick="edit()">
    <input type="button" value="삭제" onClick="delete()">
    <input type="button" value="목록" onClick="location.href='/gallery/list.jsp';">
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