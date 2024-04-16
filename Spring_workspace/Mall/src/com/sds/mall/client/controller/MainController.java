package com.sds.mall.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sds.mall.model.product.TopCategoryService;

// 고객이 보게 될 Shoppingmall 메인 요청을 처리하는 하위 컨트롤러
@Controller(value="clientMainController")
public class MainController {
//	//@RequestMapping(value="/shop", method=RequestMethod.GET)
//	@GetMapping(value="/shop")
//	public String getMain() {
//		return "shop/index";
//	}
	
// AOP로 처리
//	@Autowired
//	private TopCategoryService topCategoryService; 
	
//	// 고객이 보게 될 쇼핑몰 요청 처리
//	@RequestMapping(value="/shop", method=RequestMethod.GET)
//	public String getMain(Model model) {
////		AOP로 처리
////		List topList = topCategoryService.selectAll();
////		model.addAttribute("topList", topList);
//		return "shop/index";
//	}
	
	// 고객이 보게 될 쇼핑몰 요청 처리
	@RequestMapping(value="/shop", method=RequestMethod.GET)
	public ModelAndView getMain(Model model) {
		ModelAndView mav = new ModelAndView("shop/index");
		return mav;
	}
}
