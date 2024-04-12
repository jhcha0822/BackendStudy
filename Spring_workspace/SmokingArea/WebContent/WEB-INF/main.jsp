<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.List"%>
<%!
	// 선언 영역	
%>
<%
	// 메소드 영역
	List<SmokingArea> smokingAreaList = (List<SmokingArea>)request.getAttribute("smokingAreaList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>광진구 흡연구역</title>
<%@ include file='./inc/header.jsp' %>
<style type="text/css">
	
</style>
</head>
<script type="text/javascript">
	
	let map;
	
	// map 초기화
	function initMap() {
		let mapProp= {
		  center:new google.maps.LatLng(37.54766, 127.0850), // 광진구 좌표
		  zoom:18,
		};
		map = new google.maps.Map(document.getElementById("content"), mapProp);
	}
	
	// 마커와 정보 추가하기: 매개변수 위도, 경도
	function createMarker(lati, longi, name){
		let marker = new google.maps.Marker({
		  position:new google.maps.LatLng(lati, longi),
		  animation:google.maps.Animation.BOUNCE
		});

		marker.setMap(map);
	}
	
	// 버튼 누를때 동작
	function search() {
		// 검색한 위치로 지도 중앙값 설정

		// 주어진 거리에서 가까운 순서대로 흡연구역 검색하여 리스트에 띄우기
		/*
		$.ajax({
			url:
			type: "POST",
			data: {
				lati: $("#lati").val(),
				longi: $("#longi").val()
			},
			success: function()
		})
		*/
	}
	
	$(function(){
		// (위도,경도)를 붙여넣는 텍스트필드에 키보드에서 손을 떼면
		// 문자열을 분리하여 위도와 경도 텍스트필드에 출력
		$("#mock").on("keyup", function(){
			if($(this).val().length>15) {
				let arr = $(this).val().split(",");
				$("#lati").val(arr[0]);
				$("#longi").val(arr[1].trim()); // trim: 공백 제거				
			}
		});
		
		$("button").click(function(){
			search();
			$("#spin").toggleClass("spinner-border spinner-border-sm");
		});
	});
	
	// 한 건의 상세 데이터를 받아오기 위한 함수
	$("#areaList").click(function(){ 	
		// 현재 클릭된 Row(<tr>)
		var tr = $(this);
		var td = tr.children();
		
		// td.eq(index)로 값 받아오기
		let name = td.eq(0).text();
		let detail = td.eq(1).text();
		let dist = td.eq(2).text();
	});
	
</script>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3" id="aside">
				<p/>
				<div class="form-group">
					<label for="mock" class="form-label">좌표:</label>
					<input type="text" class="form-control form-control-sm" id="mock" placeholder="위도와 경도 입력">
				</div>
				<div class="form-group">
					<label for="lati" class="form-label">위도:</label>
					<input type="text" class="form-control form-control-sm" id="lati" placeholder="위도">
				</div>
				<div class="form-group">
					<label for="longi" class="form-label">경도:</label>
					<input type="text" class="form-control form-control-sm" id="longi" placeholder="경도">
				</div>
				<p><button type="button" class="btn btn-primary btn-block">
					<span id="spin"></span>
						검색
				</button></p>
				<table class="table table-bordered table-hover table-striped text-center" id="areaList">
					<thead>
						<tr>
							<th>장소명</th>
							<th>상세위치</th>
							<th>거리</th>
						<tr>
					</thead>
					<tbody>
					<% for(int i=0; i<smokingAreaList.size(); i++) { %>
					<%SmokingArea smokingArea = smokingAreaList.get(i); %>
						<tr>
							<td><%=smokingArea.getArea_nm()%></td>
							<td><%=smokingArea.getArea_desc()%></td>
							<td>거리</td>
						</tr>
					<% } %>
					</tbody>
				</table>
			</div>
			<div class="col-sm-9" id="content">
				지도
			</div>
		</div>
	</div>
</body>
</html>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGl4v22xE7sU94Z5rC4WzkUsxZZCCaEnM&callback=initMap"></script>