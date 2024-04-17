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
					                <label for="inputEmail3" class="col-sm-2 col-form-label">아이디</label>
					                <div class="col-sm-10">
					                    <input type="test" name="uid" class="form-control" placeholder="UID">
					                </div>
					            </div>
					            <div class="form-group row">
					                <label for="inputPassword3" class="col-sm-2 col-form-label">비밀번호</label>
					                <div class="col-sm-10">
					                    <input type="password" name="password" class="form-control" placeholder="Password">
					                </div>
					            </div>
					            <div class="form-group row">
					                <label for="inputPassword3" class="col-sm-2 col-form-label">닉네임</label>
					                <div class="col-sm-10">
					                    <input type="text" name="nickname" class="form-control" placeholder="Nickname">
					                </div>
					            </div>
					            <div class="form-group row">
					                <label for="inputPassword3" class="col-sm-2 col-form-label">이메일</label>
					                <div class="col-sm-10">
					                    <input type="text" name="email" class="form-control" placeholder="Email">
					                </div>
					            </div>
					            <div class="form-group row">
					                <label for="inputPassword3" class="col-sm-2 col-form-label">연락처</label>
					                <div class="col-sm-10">
					                    <input type="text" name="phone" class="form-control" placeholder="Phone">
					                </div>
					            </div>
					            <div class="form-group row">
					                <label for="inputPassword3" class="col-sm-2 col-form-label">주소</label>
					                <div class="col-sm-10">
					                    <input type="text" name="addr" class="form-control" placeholder="Addr">
					                </div>
					            </div>
					            <div class="form-group row">
					                <div class="offset-sm-2 col-sm-10">
					                    <button>Goole</button>
					                    <button>Naver</button>
					                    <button>Kakao</button>
					                </div>
					            </div>
					        </div>
					        <!-- /.card-body -->
					        <div class="card-footer">					            
					            <button type="button" class="btn btn-info" id="bt_login">Sign in</button>
					            <button type="button" class="btn btn-info" id="bt_regist">Sign up</button>
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
	
	function regist(){
		$.ajax({
			url:"/member/regist",
			type:"post", 
			data:{
				uid:$("input[name='uid']").val(),
				"memberDetail.password":$("input[name='password']").val(),
				nickname:$("input[name='nickname']").val(),
				email:$("input[name='email']").val(),
				"memberDetail.phone":$("input[name='phone']").val(),
				"memberDetail.addr":$("input[name='addr']").val(),
				"sns.sns_idx":4
			},
			success:function(result, status, xhr){
				console.log(result);
				if(result=="OK") {
					alert("가입 성공");
					location.href="/member/loginform";
				} else {
					alert("가입 실패");
				}
			}
		});
	}
	
	$(function(){
		$("#bt_regist").click(function(){
			regist();
		});		
	});
	
</script>
</html>