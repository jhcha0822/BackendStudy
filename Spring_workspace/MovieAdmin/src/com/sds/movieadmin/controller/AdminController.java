package com.sds.movieadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin/loginform")
	public String getLoginForm() {
		return "admin/login";
	}
	
	@GetMapping("/admin/registform")
	public String getRegistForm() {
		return "admin/regist";
	}
	
}
