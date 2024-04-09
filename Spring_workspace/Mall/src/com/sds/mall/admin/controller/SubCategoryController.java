package com.sds.mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sds.mall.model.product.SubCategoryService;

@Controller
public class SubCategoryController {
	
	// DI
	@Autowired
	private SubCategoryService subCategoryService;
	
	// 하위 카테고리 목록을 가져오되 부모인 topcategory_idx에 소속되는 목록을 가져온다
	// 단 클라이언트가 원하는 응답결과는 jsp가 아니다. 비동기방식으로는 페이지 새로고침을 원하지 않음
	// 서버측에서는 순수 데이터 형태로 데이터를 보내야 함
	
	// 아래의 메서드의 반환값이 InternalResourceViewResolver와 같은 jsp 파일명으로 해석되지 않으려면
	// 현재 이 메서드의 반환값이 데이터라는 표시를 해주어야 함 @ResponseBody
	// return 에 명시된 문자열을 전송
	@ResponseBody
	@GetMapping("/admin/subcategory/list")
	public List getSubListByTopIdx(int topcategory_idx) {
		List subList = subCategoryService.selectAllByTopIdx(topcategory_idx);
		
		// 순수 데이터만을 전송: model.setAttribute() 하지 않음
		// json으로 만들어서 전송
		// 스프링은 문자열로 응답을 보낼때; 특히 json형식으로 보낸다면
		// 자동으로 자바의 객체를 json 문자열로 변환하는 기능을 지원: jackson databind
		
		return subList;
	}
	
}
