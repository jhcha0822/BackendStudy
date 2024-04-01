<%@page import="com.sds.mvcproject.notice.model.Notice"%>
<%@page import="java.util.List"%>
<%@page import="com.sds.mvcproject.notice.model.NoticeDAO"%>	
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	NoticeDAO noticeDAO = new NoticeDAO();
%>
<%
	// 읽혀진 데이터들의 양이 많을 경우 하나의 페이지에서 전부 보여주려고 한다면 스크롤이 발생해 사용자들에게 불편함 초래
	// 데이터를 한꺼번에 보여주는 것이 아닌, 분할하여 보여주면 된다
	// 이때 분할된 각각의 단위를 페이지라 하며, 이 페이지와 관련된 산수 로직을 페이징 처리라 한다
	// 페이징 처리의 가장 근본이 되는 데이터는 총 레코드 수를 기준으로 한다
	
	List<Notice> boardList = noticeDAO.selectAll();
	
	int totalRecord = boardList.size(); // 총 레코드 수
	int pageSize = 10;    // 한 페이지에 보여줄 레코드 수
	
	// 나머지 페이지를 보여주기 위해서는 페이지 분할 번호가 필요, 이때 총 몇 페이지인가를 계산
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize); // 총 페이지 수
	
	// block: .. 6 7 8 9 10 .. 이런거
	int blockSize = 10; // 한 블럭당 몇 페이지씩 보여줄 지 결정
	
	// 클라이언트가 요청한 내용을 파악하여 몇 페이지를 보여줄지 결정
	int currentPage = 1;
	
	if(request.getParameter("currentPage") != null)
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	
	// 블럭의 시작값을 설정
	/*
	int firstPage = (currentPage - (currentPage%10) + 1);
	if(currentPage%10 == 0)
		firstPage = currentPage - 9;
	*/
	int firstPage = currentPage - (currentPage-1)%blockSize;
	int lastPage = firstPage + (blockSize - 1);	
	
	int curPos = (currentPage-1)*pageSize; // 페이지당, List의 시작 index
	
	int num=  totalRecord - curPos ; //페이지당 시작 번호 
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 게시판</title>
<%@ include file="/inc/header_link.jsp" %>
<style type="text/css">
 	/* 스타일 클래스 정의 */
 	.pageStyle{
 		font-size: 13px;
 		font-weight: bold;
 		color: red;
 	}
</style>
<script type="text/javascript">
 	// 글쓰기 폼 요청
 	$(function(){
 		$("button").click(function(){
 			location.href = "/notice/write.jsp"
 		});
 	});
</script>
</head>
<body>
<!-- AdminLTE의 테이블 사용 -->
	<div class="row">
		<div class="col-12">
		   <div class="card">
		     <div class="card-header">
		       <h3 class="card-title">공지 게시판</h3>
		       <div class="card-tools">
		         <div class="input-group input-group-sm" style="width: 150px;">
		       <input type="text" name="table_search" class="form-control float-right" placeholder="Search">
		       <div class="input-group-append">
		         <button type="submit" class="btn btn-default">
		           <i class="fas fa-search"></i>
		         </button>
		       </div>
		     </div>
		   </div>
		 </div>
		 <!-- /.card-header -->
		 <div class="card-body table-responsive p-0">
		   <table class="table table-hover text-nowrap">
		     <thead>
		       <tr>
		         <th>No</th>
		         <th>Title</th>
		         <th>Writer</th>
		         <th>Date</th>
		         <th>Read</th>
		       </tr>
		     </thead>
		     <tbody>
		     	<%for(int i=1;i<=pageSize;i++){ %>
				<%if(num<1)break; %>			     	
		       	<tr>
		       	<%
		       		// 페이지당 List의 시작 index를 이용하여 List에서 DTO를 꺼내자
		       		Notice notice = boardList.get(curPos++);
		       	%>
		         <td><%=num-- %></td>
		         <td>
		         	<a href="/notice/content.jsp?notice_idx=<%=notice.getNotice_idx()%>">
		         		<%=notice.getTitle() %>
		         	</a>
		         </td>
		         <td><%=notice.getWriter() %></td>
		         <td><%=notice.getRegdate() %></td>
		         <td><%=notice.getHit() %></td>
		       </tr>
		       <%} %>      
		     </tbody>
		   </table>
		 </div>
		 <!-- /.card-body -->
		<!-- 카드 푸터 시작 -->
			<div class="card-footer clearfix">
                <ul class="pagination pagination-sm m-0 float-right">
                  <li class="page-item">
                  	<%if(firstPage-1 > 1){ //페이지 수가 유효한 경우만..%>
                  		<a class="page-link" href="/notice/list.jsp?currentPage=<%=firstPage-1%>">«</a>
                  	<%}else{ %>
                  		<a class="page-link" href="javascript:alert('이전 페이지가 없습니다');">«</a>
                  	<%} %>       
                 </li>
                  
                  <%for(int i=firstPage;i<=lastPage;i++){%>
					<%if(i> totalPage) break;//보유한 페이지 수를 넘어서면 반복문 break; %>                  
                  	<li class="page-item">
                  		<a class="page-link  <%if(currentPage==i){%>pageStyle<%}%>" href="/notice/list.jsp?currentPage=<%=i%>"><%=i%></a>
                  	</li>
                  <%} %>
                  
                  <%if(lastPage+1 < totalPage){ %>
                  	<li class="page-item"><a class="page-link" href="/notice/list.jsp?currentPage=<%=lastPage+1%>">»</a></li>
                  <%}else{ %>
                  	<li class="page-item"><a class="page-link" href="javascript:alert('마지막 페이지입니다');">»</a></li>
                  <%} %>
                </ul>
            </div>		
		<!-- 카드 푸터 끝 -->
		
		<button>글쓰기</button>
					 
    	</div>
	</div>
</div>
</body>
</html>