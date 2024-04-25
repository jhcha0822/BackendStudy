package com.sds.movieadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	// 관리자 페이지 main 요청 처리
	@GetMapping("/")
	public String getMain() {
		System.out.println("Main 요청");
		return null;
	}
	
}
