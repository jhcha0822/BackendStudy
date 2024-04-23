package com.sds.testapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.testapp.model.notice.NoticeService;

// RestController는 REST 방싣의 개발에 최적화된 컨트롤러
// Rest는 비동기 방식과 연관성이 높다: 클라이언트가 restful한 요청을 하면 서버는 jsp, html이 아닌 순수 데이터 형태로 응답을 한다
@RestController
public class RestNoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/notice")
	public List selectAll() {
		Map map = new HashMap();
		map.put("startIndex", 0);
		map.put("rowCount", 5);
		List noticeList = noticeService.selectAll(map);
		return noticeList;
		// 본래는 List의 주소값이 나와야 하나, Boot는 이를 JSON으로 구성
		// 받아온 값을 Vue나 React를 이용하여 화면 구성
	}
	
}
