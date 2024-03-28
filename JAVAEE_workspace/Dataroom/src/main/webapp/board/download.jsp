<%@page import="java.io.OutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	// 클라이언트가 다운로드 받기 원하는 파일명을 받아오자
	String filename = request.getParameter("filename");

	// 넘어온 파일명을 이용하여 서버 측에 있는 파일에 입력 스트림 생성
	// 파일의 내용을 스트림으로 읽어서 클라이언트에게 출력할 예정
	String realPath = application.getRealPath("/data/"); // application 내장 객체는 javaEE api에서의 자료형은 ServletContext. jsp는 내장 객체로 곧바로 사용 가능
	File file = new File(realPath+filename);
	FileInputStream fis = new FileInputStream(file);
	//out.print(realPath+filename);
	
	
	// 스트림을 생성했으니 응답정보를 구성해보자
	// 응답정보는 http 프로토콜에 의해 머리와 몸체로 구성
	
	// 어플리케이션 내장객체로 하여금 mimetype을 조사
	// text/html, application/json, image/jpeg 등. 알수없는 타입도 존재
	String mimeType = application.getMimeType(realPath+filename);
	//out.print(mimeType);
	
	// 표준을 벗어난 알 수 없는 형식의 마임타입의 경우엔 getMimeType()가 null을 반환함
	if(mimeType == null){ // 일반적으로 바이너리 파일과 알 수 없는 형식의 마임타입
		mimeType = "application/octet-stream"; // 8비트 형식의 일반적인 처리
	}
	response.setContentType(mimeType);
	
	// 응답 헤더 구성하기
	response.setHeader("Content-Dispostion", "attachment; filename=\""+filename+"\""); // 첨부된 파일을 다운: attachment
	
	// 파일의 용량
	response.setContentLength((int)file.length()); // 필수는 아님
	
	// 입력스트림으로 데이터 읽어들여 클라이언트에게 출력
	// 자료실에서 다루는 파일들은 (읽기 위함이 아닌) 바이너리 파일이므로 바이트 기반의 출력 스트림으로 처리
	OutputStream os = response.getOutputStream();
	
	int data = -1;
	
	while(true){
		data = fis.read();
		if(data==-1)
			break;
		os.write(data);
	}
	fis.close();
	os.close();	
%>