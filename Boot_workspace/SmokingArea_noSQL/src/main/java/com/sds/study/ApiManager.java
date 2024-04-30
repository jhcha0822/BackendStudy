package com.sds.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.sds.study.domain.Spot;

public class ApiManager {
	@Autowired
	private Spot spot;
	
	Map<String, JSONObject> jsonMap = new HashMap<>();

	// 1. URL을 만들기 위한 StringBuilder
	StringBuilder urlBuilder = new StringBuilder(
			"http://apis.data.go.kr/3040000/smokingService/getSmkAreaList"); /* URL */
	// 2. 오픈 API의요청 규격에 맞는 파라미터 생성, 발급받은 인증키.

	public void callBack() throws UnsupportedEncodingException, IOException, JSONException {
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=5XuhuoQexLdGRNEAvAnhIOWFjP6tCKgUahcCE1nzgw1OlpZmm0p%2B5Le%2FAaef8tPbhXKeRHAvqZzw6SwZZ0aCFQ%3D%3D"); /*
																														 * Service
																														 * Key
																														 */
		urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* 응답메시지 결과 요청 타입(xml, json, geojson) */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("40", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
//	    urlBuilder.append("&" + URLEncoder.encode("id","UTF-8") + "=" + URLEncoder.encode("군자동-02-01-020", "UTF-8")); /*흡연구역 아이디 전체 출력은 빈칸*/
		urlBuilder
				.append("&" + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 전체 출력은 빈칸 */

		// 3. URL 객체 생성
		URL url = new URL(urlBuilder.toString());
		// 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 5. 통신을 위한 메소드 SET.
		conn.setRequestMethod("GET");
		// 6. 통신을 위한 Content-type SET.
		conn.setRequestProperty("Content-type", "application/json");
		// 7. 통신 응답 코드 확인.
		System.out.println("Response code: " + conn.getResponseCode());
		// 8. 전달받은 데이터를 BufferedReader 객체로 저장.
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		// 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
		/*
		 * StringBuilder sb = new StringBuilder(); String line; while ((line =
		 * rd.readLine()) != null) { sb.append(line); }
		 */

		// BufferedReader를 사용하여 데이터를 읽어옴
		String line;
		while ((line = rd.readLine()) != null) {
			// JSON 데이터를 JSONObject로 파싱하여 맵에 추가
			JSONObject jsonObject = new JSONObject(line);

			// JSON 객체의 고유한 식별자를 키로 사용하여 맵에 추가
			jsonMap.put(jsonObject.getString("id"), jsonObject);
		}

		// 10. 객체 해제
		rd.close();
		conn.disconnect();
	}

}
