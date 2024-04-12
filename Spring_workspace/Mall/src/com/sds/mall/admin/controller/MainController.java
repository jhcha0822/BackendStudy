package com.sds.mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sds.mall.model.product.TopCategoryService;

// 관리자 모드 메인의 요청을 처리하는 하위 컨트롤러
// 개발자가 @Controller에 아무것도 명시하지 않으면 해당 클래스는 인스턴스화 시키며 Spring이 자동으로 bean의 id를 소문자로 시작하는 클래스명을 할당
// 원하는 id가 있다면 value 속성에 지정하면 된다
@Controller(value="adminMainController")
public class MainController {
		
	// <mvc:annotation-driven/> 등록 이후부터 SpringMVC에서 컨트롤러 영역의 많은 기능을 지원
	// @RequestMapping(value="", method=RequestMethod.GET)도 줄여쓰기 가능
	
	// "/": localhost:7777/ 까지를 의미
	// web.xml에서 관리자와 관련된 루트는 /admin/*으로 바꾸어두었음
	@GetMapping("/main")
	public String getMain() {
		return "admin/index";
	}
	
}
