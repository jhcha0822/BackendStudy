<%@page import="com.sds.model2app.domain.Board"%>
<%@page import="com.sds.model2app.common.Pager"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%!
	Pager pager = new Pager();
%>
<%
	List<Board> boardList = (List)request.getAttribute("boardList");
	// out.print("게시물 수: "+boardList.size());
	pager.init(boardList, request); // 페이징 처리
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <%@ include file='/inc/header.jsp' %>
  <script type="text/javascript">
  	$(function(){
  		$("button").click(function(){
  			location.href="/board/write.jsp"
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
      <%
      	// page당 list의 시작 인덱스 가져오기
      	int curPos = pager.getCurPos();
      	int num = pager.getNum();
      %>
      <% for(int i=0; i<pager.getPageSize(); i++){ %>
      <% if(num<1) break; // 게시물 번호가 0 도달 %>
      <% Board board = boardList.get(curPos++); %>
      <tr>
        <td><%= num-- %></td>
        <td><a href="/board/detail.do?board_idx=<%=board.getBoard_idx()%>"><%= board.getTitle() %></a></td>
        <td><%= board.getWriter() %></td>
        <td><%= board.getRegdate().substring(0, 10) %></td>
        <td><%= board.getHit() %></td>
      </tr>
      <% } %>
      <tr>
      	<td colspan="5">
      	  <button class="btn btn-primary">등록</button>
      	</td>      	
      </tr>
      <tr>
      	<td comspan="5">
      	  <!-- Center-aligned -->
			<ul class="pagination justify-content-center" style="margin:20px 0">
			   <% for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++) { %>
			     <% if(i > pager.getTotalPage()) break; %>
			   	   <li class="page-item <%if(i == pager.getCurrentPage()){%>active<%}%>">
			   	     <a class="page-link" href="/board/list.do?currentPage=<%=i%>"><%=i%></a>
			   	   </li>
			   <% } %>
			</ul>
      	</td>
      </tr>
    </tbody>
  </table>
</div>

</body>
</html>
