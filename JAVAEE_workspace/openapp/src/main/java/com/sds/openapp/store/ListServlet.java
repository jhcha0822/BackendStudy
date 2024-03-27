package com.sds.openapp.store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 맛집 목록 요청을 처리하는 서블릿
public class ListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String perPage = request.getParameter("perPage");
		
		// System.out.println("perPage: "+perPage);
		
		// 충청도 openAPI 서버에 Http 요청을 시도
		String serviceURL = "http://apis.data.go.kr/6430000/cbRecreationalFoodInfoService/getRecreationalFoodInfo";
		String serviceKey = "MRUycLhdpYyEV9wjmh5lVJ9uT6mWLVSzuIADNw/kWLSbOTeMczhhUFjBaVJx/WWl/mPpxGANRZrvFx6VkTeJHg==";
		
		StringBuilder sb = new StringBuilder();
		sb.append(serviceURL);
		sb.append("?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8"));
		sb.append("&perPage=" + URLEncoder.encode(perPage, "UTF-8"));
		
		URL url = new URL(sb.toString());
		URLConnection urlCon = url.openConnection();
		HttpURLConnection con = (HttpURLConnection)urlCon;
		
		// Http 헤더 구성
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		
		// 응답 가져오기
		int result = con.getResponseCode();
		InputStream is = null; // 바이트 기반의 입력 스트림
		InputStreamReader reader = null; // 문자 기반의 입력 스트림
		BufferedReader buffr = null; // 버퍼 처리된 문자 기반의 입력 스트림
		
		if(result >= 200 && result<=300) {
			// 서버로부터 json 문자열 읽어들이기. 스트림 필요
			System.out.println("OpenAPI 서버로부터 응답을 가져옴");
			is = con.getInputStream(); // 바이트 기반의 입력 스트림
			reader = new InputStreamReader(is); // 문자 기반으로 업그레이드
			buffr = new BufferedReader(reader); // 버퍼 스트림으로 업그레이드
			
			// 한줄씩 모두 읽기
			String str = null;
			while(true) {
				str = buffr.readLine();
				if(str == null)
					break;
				System.out.println(str);
				// 응답 정보가 가진 PrintWriter에 json 문자열을 쌓아두기
				out.print(str);
			}			
		}
		if(is != null)
			is.close();
		if(reader != null)
			reader.close();
		if(buffr != null)
			buffr.close(); // throws 존재로 인하여 try-catch 하지 않음
		con.disconnect();
	}
	
}
