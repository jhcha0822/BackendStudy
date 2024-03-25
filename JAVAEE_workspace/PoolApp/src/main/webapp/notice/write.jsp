<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<%@ include file="/inc/header_link.jsp" %>
<script type="text/javascript">
	function regist() {
		// 비동기 글쓰기 요청
		let xhttp = new XMLHttpRequest();
		xhttp.open("POST", "/notice/regist"); // servelt에 요청
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); // POST 방식일 때 필요
		// 비동기 방식으로 요청을 시도하면, 요청을 담당했던 객체인 xhttp가 언제 요청에 대한 응답을 반환했는지 확인 필요
		// onreadystatechange 속성을 이용해 시점을 감지
		xhttp.onreadystatechange = function(){ // 요청 상태를 알려주는 속성인 readyState의 값이 바뀔때마다 익명함수 callback으로 호출
			if(this.readyState==4 && this.status==200){
				console.log("서버에서 보내온 결과는 ", this.responseText);
			}
		}
		let title = $("#title").val();
		let writer = $("#writer").val();
		let content = $("#content").val();
		
		xhttp.send("title="+title+"&writer="+writer+"&content="+content);
	}
	
	$(function(){
		$("#bt_regist").click(function(){
			regist();	
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
                      <input type="text" class="form-control" placeholder="제목 입력" id="title">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="inputPassword3" class="col-sm-2 col-form-label">작성자</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" placeholder="작성자 입력" id="writer">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="inputPassword3" class="col-sm-2 col-form-label">내용</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" placeholder="내용 입력" id="content">
                    </div>
                  </div>
                </div>
                <!-- /.card-body -->
                <div class="card-footer">
                  <button type="button" class="btn btn-info" id="bt_regist">작성</button>
                  <button type="button" class="btn btn-default float-right" id="bt_list">취소</button>
                </div>
                <!-- /.card-footer -->
              </form>
            </div>
		</div>
	</div>
</body>
</html>