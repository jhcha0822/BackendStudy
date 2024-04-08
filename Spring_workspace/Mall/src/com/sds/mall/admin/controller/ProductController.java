package com.sds.mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sds.mall.model.product.TopCategoryService;

// 관리자 모드에서 상품 관련 요청 처리 하위 컨트롤러
@Controller
public class ProductController {
	
	// 부장님에게 일 시키기
	@Autowired
	private TopCategoryService topCategoryService;
	
	// 상품 등록 폼 요청 처리: Getmapping 요청 시 아래 함수 수행
	@GetMapping("/admin/product/registform")
	public String getRegistForm(Model model) {
		// 3) model에 전달
		List topList = topCategoryService.selectAll();
		
		// 4) 결과 저장
		model.addAttribute("topList", topList);
		
		return "admin/product/regist";
	}
	
}
