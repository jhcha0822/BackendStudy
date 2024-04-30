<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Dashboard</title>
	
  <%@ include file="./inc/header_link.jsp" %>
  
</head>
<body class="hold-transition sidebar-mini layout-fixed">

<div class="wrapper">
	<div class="card card-dark mt-5 ml-5 mr-5">
	    <div class="card-header">
	      <h3 class="card-title">관리자 등록</h3>
	    </div>
	    <!-- /.card-header -->
	    <!-- form start -->
	    <form class="form-horizontal">
	      <div class="card-body">
	        <div class="form-group row">
	          <label for="inputEmail3" class="col-sm-2 col-form-label">ID</label>
	          <div class="col-sm-10">
	            <input type="text" class="form-control" name="admin_id" placeholder="ID">
	          </div>
	        </div>
	        <div class="form-group row">
	          <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
	          <div class="col-sm-10">
	            <input type="password" class="form-control" name="admin_pw" placeholder="Password">
	          </div>
	        </div>
	        <div class="form-group row">
	          <label for="inputEmail3" class="col-sm-2 col-form-label">Username</label>
	          <div class="col-sm-10">
	            <input type="text" class="form-control" name="admin_name" placeholder="Username">
	          </div>
	        </div>
	      </div>
	      <!-- /.card-body -->
	      <div class="card-footer">
	        <button type="button" class="btn btn-dark" id="bt_login">관리자 로그인</button>
	        <button type="button" class="btn btn-dark" id="bt_regist">관리자 등록</button>
	      </div>
	      <!-- /.card-footer -->
	     </form>
	</div>
	<%@ include file="./inc/footer_link.jsp" %>
</body>
</html>
<<script type="text/javascript">

	function regist() {
		$.ajax({
			url:"/admin",
			type:"post",
			data:$("form").serialize(),
			success:function(result, status, xhr){
				alert("관리자 등록 성공");
				location.href="/admin/main";
			},
			error:function(xhr, status, err){
				alert("관리자 등록 실패");
			}
		})
	}

	$(function(){
		$("#bt_regist").click(function(){
			regist();
		});
	});

</script>