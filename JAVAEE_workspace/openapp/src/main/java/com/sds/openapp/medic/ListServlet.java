package com.sds.openapp.medic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

// 병원 목록 요청을 처리하는 서블릿

public class ListServlet extends HttpServlet {
	String serviceKey = "";
	// 클라이언트의 요청은 get 방식임

	SAXParserFactory factory;
	SAXParser parser;
	MedicHandler handler = null;
	PrintWriter out;
	
	public ListServlet() {
		factory = SAXParserFactory.newInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=utf-8");
		out = response.getWriter();
		
		String code = request.getParameter("code");
		String numOfRows = request.getParameter("numOfRows");
		System.out.println("클라이언트가 전송한 지역 코드: "+code);	
		
		// html과의 관계에서, tomcat이 서버가 되지만
		// openapi 에게 tomcat은 클라이언트이다
		// tomcat이 외부에 웹서버에 요청을 시도해야 하므로
		// javaSE.HttpURLConnection 객체를 사용한다
		
		// 요청 주소 문자열이 너무 길기 때문에 StringBuilder를 이용한다
		StringBuilder sb = new StringBuilder();
		sb.append("https://apis.data.go.kr/B551182/hospInfoServicev2/getHospBasisList"); // url
		sb.append("?serviceKey="+URLEncoder.encode(serviceKey, "UTF-8")); // 인증키
		sb.append("&numOfRows="+URLEncoder.encode(numOfRows, "UTF-8"));  // n건 가져오기
		sb.append("&sidoCd="+URLEncoder.encode(code, "UTF-8"));
		
		
		URL url = new URL(sb.toString());
		URLConnection urlCon = url.openConnection();
		HttpURLConnection con = (HttpURLConnection)urlCon;
		
		// API 서버에 웹요청 (요청 Header 구성)
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "text/xml");
		
		// 반환값을 통한 판단
		int status = con.getResponseCode(); // 성공일 경우 200
		
		// 서버로부터 받은 데이터를 이용해 XML을 분석한 뒤 java가 이해하는 구조로 변환
		// XML -> List
		
		URI uri = null;
		try {
			uri = url.toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		if(status == 200 && status<=300) {
			try {
				parser = factory.newSAXParser(); // Parser 생성
				parser.parse(uri.toString(), handler = new MedicHandler(this));
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			
		
//		InputStreamReader reader = null;
//		BufferedReader buffr = null; // 한 줄씩 읽어들이는 버퍼처리된 문자기반의 입력스트림
//		
//		if(status == 200) {
//			 reader = new InputStreamReader(con.getInputStream()); // 바이트 기반 스트림 -> 문자 기반 스트림
//			 buffr = new BufferedReader(reader);                   // 문자 기반 스트림 -> 버퍼 처리된 스트림
//			 
//			 // 데이터 한 줄씩 읽어들이기
//			 String str = null;
//			 while(true) {
//				 str = buffr.readLine();
//				 if(str == null) break;
//				 // System.out.println(str);
//			 }
//			 
//			 // 스트림 닫기
//			 if(buffr != null)
//				 buffr.close();
//			 if(reader != null)
//				 reader.close();
			 
			 // 연결 객체 접속 해제
			 con.disconnect();
		}
	}
	
	// 클라이언트에게 응답정보를 구성하는 메서드를 따로 정의
	public void createResponseData() {
		System.out.println("조회된 병원 수: "+handler.list.size());
		
		// java가 이해할 수 있는 형태인 json 문자열로 전송
		//sb.delete(0, sb.length()); // 기존의 sb 초기화
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		sb.append("\"hospitalList\" : [");
		
		for(int i=0;i<handler.list.size();i++) {
			Hospital hospital = handler.list.get(i); // 리스트에서 i번째의 요소를 꺼내기
			sb.append("{");
			sb.append("\"name\":\""+hospital.getName()+ "\",");
			sb.append("\"addr\" :\""+hospital.getAddr()+ "\", ");
			sb.append("\"lati\": "+hospital.getLati()+","); 
			sb.append("\"longi\" :"+hospital.getLongi()+"");
			
			if(i < handler.list.size()-1) {
				sb.append("},"); // list의 사이즈에서 -1 뺀수보다 작은 경우까지
			}else {
				sb.append("}"); 
			}
		}
		sb.append("]");
		sb.append("}");		
		
		// Sb에 있는 문자열을 웹브라우저 클라이언트에 응답 정보로 전송
		out.print(sb.toString());	
		
		
	}
}
