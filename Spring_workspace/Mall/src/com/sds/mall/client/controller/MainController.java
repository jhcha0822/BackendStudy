package com.sds.mall.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 고객이 보게 될 Shoppingmall 메인 요청을 처리하는 하위 컨트롤러
@Controller(value="clientMainController")
public class MainController {
	//@RequestMapping(value="/shop", method=RequestMethod.GET)
	@GetMapping(value="/shop")
	public String getMain() {
		return "shop/index";
	}
}
