package com.sds.mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// admin-context.xml에서 접근
@Controller
public class TestController {
	
	// localhost:777/admin/test 로 접근
	@GetMapping("/test")
	public String getTest() {
		System.out.println("test");
		return null;
	}
}
