<%@page import="com.sds.mvcframework.notice.model.Notice"%>
<%@page import="com.sds.mvcframework.notice.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%!
	NoticeDAO noticeDAO = new NoticeDAO(); 
%>
<%
	String notice_idx = request.getParameter("notice_idx");
	Notice notice = noticeDAO.select(Integer.parseInt(notice_idx));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<%@ include file="/inc/header_link.jsp" %>
<script type="text/javascript">
	
	function edit() {
		// 동기, 일반적 방식의 전송
		$("form").attr({
			action:"/notice/edit", // servlet
			method:"post"
		});
		
		$("form").submit(); // 전송
	}
	
	$(function(){
		$("#bt_edit").click(function(){
			if(confirm("수정하시겠습니까?"))
				edit();	
		});
		
		$("#bt_list").click(function(){
			location.href="/notice/list.jsp";
		});
	});
</script>
</head>
<body>
	<div class="row">
		<div class="col-12">
			<div class="card card-info">
              <div class="card-header">
                <h3 class="card-title">글쓰기</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form class="form-horizontal">
                <div class="card-body">
                  <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">제목</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" value="<%=notice.getTitle() %>" id="title" name="title">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="inputPassword3" class="col-sm-2 col-form-label">작성자</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" value="<%=notice.getWriter() %>" id="writer" name="writer">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="inputPassword3" class="col-sm-2 col-form-label">내용</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" value="<%=notice.getContent() %>" id="content" name="content">
                    </div>
                  </div>
                </div>
                <!-- /.card-body -->
                <div class="card-footer">
                  <button type="button" class="btn btn-info" id="bt_edit">수정</button>
                  <button type="button" class="btn btn-info" id="bt_del">삭제</button>
                  <button type="button" class="btn btn-default float-right" id="bt_list">목록</button>
                </div>
                <!-- /.card-footer -->
              </form>
            </div>
		</div>
	</div>
</body>
</html>