package com.sds.mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 관리자 모드 메인의 요청을 처리하는 하위 컨트롤러
// 개발자가 @Controller에 아무것도 명시하지 않으면 해당 클래스는 인스턴스화 시키며 Spring이 자동으로 bean의 id를 소문자로 시작하는 클래스명을 할당
// 원하는 id가 있다면 value 속성에 지정하면 된다
@Controller(value="adminMainController")
public class MainController {
	
	// <mvc:annotation-driven/> 등록 이후부터 SpringMVC에서 컨트롤러 영역의 많은 기능을 지원
	// @RequestMapping(value="", RequestMethod.GET)도 줄여쓰기 가능
	@GetMapping("/admin")
	public String getMain() {
		return "admin/index";
	}
	
}
