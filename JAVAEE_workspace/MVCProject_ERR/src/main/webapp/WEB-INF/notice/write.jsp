<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	function regist() {
		$("form").attr({
			method:"post",
			action:"/board/regist.do"
		});
		$("form").submit();
	}
	
	$(function(){
		$("#bt_regist").click(function(){
			regist();	
		});
		
		$("#bt_list").click(function(){
			location.href="/board/list.do";
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
                      <input type="text" class="form-control" placeholder="제목 입력" name="title">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="inputPassword3" class="col-sm-2 col-form-label">작성자</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" placeholder="작성자 입력" name="writer">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="inputPassword3" class="col-sm-2 col-form-label">내용</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" placeholder="내용 입력" name="content">
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