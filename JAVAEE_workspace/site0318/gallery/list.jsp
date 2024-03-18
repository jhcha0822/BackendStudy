<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%
  Class.forName("oracle.jdbc.driver.OracleDriver");
  
  Connection con = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;

  String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="seshop";
	String pass="1234";

  con=DriverManager.getConnection(url, user, pass);

  String sql="select * from gallery order by gallery_idx desc"; //내림차순
	pstmt=con.prepareStatement(sql); //쿼리수행 객체 준비

	//쿼리실행(select문이므로, 쿼리 실행 후 그 결과표 반환되어 ResultSet 으로 받자)
	rs = pstmt.executeQuery(); //select문의 경우 executeQuery() 사용해야 함 
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    $(function() {
        // jquery의 syntax: (누구).어떻게()
        // 선택자 (누구)에는 css의 선택자 모두 가능
        $("button").click(function(){
            // 서버에 있는 자원을 요청
            // 개발자가 요청 방법을 지정하지 않으면 get방식
            location.href="/gallery/write.jsp";
        });
    });
</script>
</head>
<body>

<h2>Zebra Striped Table</h2>
<p>For zebra-striped tables, use the nth-child() selector and add a background-color to all even (or odd) table rows:</p>

<table>
  <tr>
    <th>No</th>
    <th>이미지</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
  <%
    while(rs.next()) {
  %>
  <tr>
    <td><%=rs.getInt("gallery_idx")%></td>
    <td><img width="45px" src="/data/<%=rs.getString("filename")%>"></td>
    <td>
      <a href="/gallery/content.jsp?gallery_idx=<%=rs.getInt("gallery_idx")%>"><%=rs.getString("title")%></a>
    </td>
    <td><%=rs.getString("writer")%></td>
    <td><%=rs.getString("regdate")%></td>
    <td><%=rs.getInt("hit")%></td>
  </tr>
  <%
    }
  %>
  <tr>
    <td colspan="6">
        <button>글쓰기</button>
    </td>
  </tr>
</table>

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