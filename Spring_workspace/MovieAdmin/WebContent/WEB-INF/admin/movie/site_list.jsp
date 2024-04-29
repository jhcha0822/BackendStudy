<%@page import="com.sds.movieadmin.domain.Movie"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	// 리스트 꺼내기
	List<Movie> movieList = (List)request.getAttribute("movieList");
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

	<!-- Preloader -->
	<%@ include file="../inc/preloader.jsp" %>
	
  <!-- Navbar -->
  <%@ include file="../inc/navi.jsp" %>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
	<%@ include file="../inc/sidebar.jsp" %>
  
  <!-- /.sidebar -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">상품등록</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">상품관리</a></li> 
              <li class="breadcrumb-item active">상품등록</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
			
		<!-- 카드 영역 begin -->
		<!-- /.table -->
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Responsive Hover Table</h3>

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
                      <th>이미지</th>
                      <th>영화코드</th>
                      <th>영화명</th>
                      <th>영화유형</th>
                      <th>국가명</th>
                      <th>제작년도</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                    <% for(int i=0;i<10;i++) {%>
                   	<% Movie movie = movieList.get(i); %>
                        <tr>
                            <td><%=i%></td>
                            <td><%=movie.getMovieCd() %></td>
                            <td><%=movie.getMovieNm() %></td>
                            <td><%=movie.getTypeNm() %></td>
                            <td><%=movie.getNationAlt() %></td>
                            <td><%=movie.getPrdtYear() %></td>
                        </tr>
                    <% } %>
                    
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
        </div>
        <!-- /.table -->
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
	function getSubCategoryList(topcategory_idx) {
		// 서버에 하위 카테고리 목록을 요청하되, 비동기 방식으로 요청
		// select * from subcategory where topcategory_idx = 3
		$.ajax({
			url:"/admin/subcategory/list?topcategory_idx="+topcategory_idx, 
			type:"GET", 
			success:function(result, status, xhr) {
				console.log("서버의 응답 정보는 ",  result);
				// 하위카테고리 컨트롤러 selectbox에 채우기
				// $("#sub").html("<option></option>"); 				// innerHtml로 옵션 채우기: 1쌍만 채워짐
				let op = "<option value=\"0\">하위 카테고리 선택▼</option>"; 	// 변수로 설정
				for(let i=0; i<result.length; i++) {
					let json = result[i];
					op += "<option value=\""+json.subcategory_idx+"\">"+json.subname+"</option>";
				}
				$("#sub").html(op);
			}			
		});	
	}
	
	function regist() { // 상품 입력 정보 전송
		$("form").attr({
			action:"/admin/movie/regist",
			method:"POST",
			// 텍스트 데이터와 바이너리 데이터가 섞여 있는 복합 데이터 전송: enctype multipart/form-data
			enctype:"multipart/form-data"
		});
		$("form").submit();
	}
	
	// onload
	$(function(){
		$("#content").summernote({
			height:200, 
			placehodel:"상품 상세 설명 입력"
		});
		
		// AJAX로 상위 카테고리의 아이템을 변경하면 서브 카테고리의 목록을 가져온다
		$("select[name='top']").change(function(){
			console.log($(this).val());
			getSubCategoryList($(this).val());
		});
		
		$("#bt_regist").click(function(){
			regist();
		});
		
		$("#bt_list").click(function(){
			list();
		});
		
	});
</script>