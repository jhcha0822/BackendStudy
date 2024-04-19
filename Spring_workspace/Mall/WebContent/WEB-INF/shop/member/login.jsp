<%@ page contentType="text/html;charset=utf-8"%>
<%

%>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ashion | Template</title>
	
	<%@ include file="../inc/header_link.jsp" %>	
	
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>
	
	<%@ include file="../inc/header.jsp" %>
	

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="./index.html"><i class="fa fa-home"></i> Home</a>
                        <a href="#">Women’s </a>
                        <span>Essential structured blazer</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Login Section Begin -->
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
					<div class="card card-info">
					    <div class="card-header">
					        <h3 class="card-title">Login</h3>
					    </div>
					    <!-- /.card-header -->
					    <!-- form start -->
					    <form class="form-horizontal">
					        <div class="card-body">
					            <div class="form-group row">
					                <label for="inputEmail3" class="col-sm-2 col-form-label">ID</label>
					                <div class="col-sm-10">
					                    <input type="text" class="form-control" name="uid" placeholder="UID">
					                </div>
					            </div>
					            <div class="form-group row">
					                <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
					                <div class="col-sm-10">
					                    <input type="password" class="form-control" name="password" placeholder="Password">
					                </div>
					            </div>
					            <div class="form-group row">
					                <div class="offset-sm-2 col-sm-10">
					                    <button type="button" id="bt_google">Google</button>
					                    <button type="button" id="bt_naver">Naver</button>
					                    <button type="button" id="bt_kakao">Kakao</button>
					                </div>
					            </div>
					        </div>
					        <!-- /.card-body -->
					        <div class="card-footer">					            
					            <button type="button" id="bt_login" class="btn btn-info">Sign in</button>
					            <button type="button" id="bt_regist" class="btn btn-info">Sign up</button>
					        </div>
					        <!-- /.card-footer -->
					    </form>
					</div>
                </div>
            </div>
        </div>
    </section>
    <!-- Login Section End -->

	

	<%@ include file="../inc/insta.jsp" %>
	
	<!-- Footer Section Begin -->
	<%@ include file="../inc/footer.jsp" %>
	<!-- Footer Section End -->
	
	<!-- Search Begin -->
	<%@ include file="../inc/search.jsp" %>
	<!-- Search End -->
	
	<!-- Js Plugins -->
	<%@ include file="../inc/footer_link.jsp" %>
</body>
<script type="text/javascript">
	function login() {
		$.ajax({
			url:"/member/login",
			type:"post",
			data:{
				uid:$("input[name='uid']").val(),
				"memberDetail.password":$("input[name='password']").val()
			},
			success:function(result, status, xhr){
				// console.log(result);
				if(result=="OK"){
					alert("로그인 성공");
					location.href="/shop";
				} else {
					alert("로그인 실패");
				}
			}
		});
	}
	
	// 인증 화면
	function gotoAuthForm(sns) {
		// 비동기 방식으로 sns마다 지정된 url 정보 얻어오기
		$.ajax({
			url:"/rest/member/authform/"+sns,
			type:"get",
			success:function(result, status, xhr){
				console.log(result);
				// location.href="result";
			}
		});
	}
	
	$(function(){
		$("#bt_login").click(function(){
			login();
		});
		
		$("#bt_google").click(function(){
			gotoAuthForm("google");	
		});
		
		$("#bt_naver").click(function(){
			gotoAuthForm("naver");		
		});
				
		$("#bt_kakao").click(function(){
			gotoAuthForm("kakao");
		});
	});
</script>
</html>