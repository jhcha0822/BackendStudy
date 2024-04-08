package com.sds.mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SubCategoryController {
	
	// 하위 카테고리 목록을 가져오되 부모인 topcategory_idx에 소속되는 목록을 가져온다
	// 단 클라이언트가 원하는 응답결과는 jsp가 아니다. 비동기방식으로는 페이지 새로고침을 원하지 않음
	// 서버측에서는 순수 데이터 형태로 데이터를 보내야 함
	
	// 아래의 메서드의 반환값이 InternalResourceViewResolver와 같은 jsp 파일명으로 해석되지 않으려면
	// 현재 이 메서드의 반환값이 데이터라는 표시를 해주어야 함 @ResponseBody
	@ResponseBody
	@GetMapping("/admin/subcategory/list")
	public String getSubListByTopIdx() {
		
		return "test";
	}
	
}
