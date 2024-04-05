<%@page import="java.util.List"%>
<%@page import="com.sds.spring.domain.Board"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Board> boardList = (List)request.getAttribute("boardList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 보안 폴더이기에 상대경로로 표시 -->
<%@ include file="../inc/header_link.jsp" %>
<script type="text/javascript">
	$(function(){
		$("button").click(()=>{
			location.href="/board/writeform"; // 글쓰기 폼 요청: WEB-INF/ 내 .jsp 접근은 하위 컨트롤러를 거쳐야 함 
		});
	});
</script>
</head>
<body>
<div class="container">
  <h2>Hoverable Dark Table</h2>
  <p>The .table-hover class adds a hover effect (grey background color) on table rows:</p>            
  <table class="table table-dark table-hover">
    <thead>
      <tr>
        <th>No</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
      <% for(int i=0; i<boardList.size(); i++) { %>
      <% Board board = boardList.get(i); %>
      <tr>
        <td><%= i %></td>
        <td>
        	<a href="/board/detail?board_idx=<%= board.getBoard_idx() %>"><%= board.getTitle() %></a>
        </td>
        <td><%= board.getWriter() %></td>
        <td><%= board.getContent() %></td>
        <td><%= board.getHit() %></td>
      </tr>
      <% } %>
      <tr>
      	<td colspan="5">
      		<button class="btn btn-primary">글쓰기</button>
      	</td>
      </tr>
    </tbody>
  </table>
</div>
</body>
</html>