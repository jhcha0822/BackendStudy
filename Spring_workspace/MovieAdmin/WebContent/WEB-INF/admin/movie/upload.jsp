<%@page import="com.sds.movieadmin.domain.Movie"%>
<%@page import="com.sds.movieadmin.domain.MovieType"%>
<%@page import="com.sds.movieadmin.domain.Nation"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//제너릭으로 선언하여, 아래쪽 코드에서 형변환의 불편함이 없도록 하자 
	List<Nation> nationList = (List)request.getAttribute("nationList");
	List<MovieType> movieTypeList = (List)request.getAttribute("movieTypeList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Dashboard</title>

	<%@ include file="../inc/header_link.jsp" %>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    
    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
			
			<!-- 카드 영역 begin -->
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">엑셀 파일 업로드</h3>
					<div class="card-tools">
					<button type="button" class="btn btn-tool" data-card-widget="collapse">
					<i class="fas fa-minus"></i>
					</button>
					<button type="button" class="btn btn-tool" data-card-widget="remove">
					<i class="fas fa-times"></i>
					</button>
					</div>
				</div>
			
				<!-- 카드의 body 영역 begin -->
				<form>
					<div class="card-body" style="display: block;">					
						<!-- 입력 폼이 나올 row 시작  -->
						<div class="row">
							
							<div class="col-md-12">
								<div class="form-group">
									<input name="file" type="file" class="form-control"> 
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<button type="button" class="btn btn-primary" id="bt_excel">등록</button>
								</div>
							</div>
							
						</div>	
						<!-- 입력 폼이 나올 row 끝  -->
						
					</div>
				</form>
				<!-- 카드의 body 영역 end -->			      		
        	</div>
        	<!-- 카드 영역 end -->
        
      <!-- /.container-fluid -->
      
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
	<%@ include file="../inc/footer.jsp" %>  

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->
	
<%@ include file="../inc/footer_link.jsp" %>	

</body>
</html>
<script type="text/javascript">
	
	// 비동기 업로드 요청
	function upload() {
		let fileData = $("form input[name='file']").prop("files")[0]; // 파일 컴포넌트 접근: 컴포넌트 n개-> n번째까지 사용 가능
		let formData = new FormData();
		// 얻어온 파일 컴포넌트를 formData에 추가
		formData.append("file", fileData);	
		$.ajax({
			url:"/excel/movie",
			type:"post",
			// data:$("form").serialize(), : 데이터를 문자열로만 변경하기에 사용 X
			data:formData,
			contentType:false, // 헤더값을 false로 두어 문자열 처리 금지
			processData:false, // 문자열로 변환 금지, 바이너리 파일로 사용
			success:function(result, status, xhr){
				alert("등록 성공");
			},
			error:function(xhr, status, err){
				console.log("업로드 에러", xhr.status);
				if(xhr.status == 413)
					alert("파일 용량을 확인해주십시오");
				else
					alert("등록 실패");
			}			
		});
	}
	
	// onload
	$(function(){		
		$("#bt_excel").click(function(){
			upload();
		});
	});
</script>