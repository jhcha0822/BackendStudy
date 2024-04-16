package com.sds.mall.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sds.mall.domain.Product;
import com.sds.mall.model.common.FormatManager;
import com.sds.mall.model.product.ProductService;
import com.sds.mall.model.product.TopCategoryService;

// (쇼핑몰에서) 상품과 관련된 요청을 처리하는 컨트롤러
@Controller
public class ProductController {
	
	@Autowired
	private TopCategoryService topCategoryService; 
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FormatManager formatManager;
	
	// 목록 요청
	@GetMapping("/product/list") // /product/list 로 요청이 들어오면
	public ModelAndView getList( // Model model, 
			@RequestParam(value="topcategory_idx", defaultValue="0") int topcategory_idx,
			@RequestParam(value="subcategory_idx", defaultValue="0") int subcategory_idx) {

		// 카테고리 가져오기
//		List topList = topCategoryService.selectAll();

		List productList = null;
		// 상품 목록 가져오기
		if(topcategory_idx>0) { // 파라미터가 넘어온 경우
			productList = productService.selectAllByTopIdx(topcategory_idx);
		} else if(subcategory_idx>0) {
			productList = productService.selectAllBySubIdx(subcategory_idx);
		} else {
			productList = productService.selectAll(); // 모든 상품 가져오기
		}	
		
		ModelAndView mav = new ModelAndView();
		
//		model.addAttribute("topList", topList);
//		model.addAttribute("productList", productList);
//		model.addAttribute("formatManager", formatManager);

		mav.addObject("productList", productList);
		mav.addObject("formatManager", formatManager);
		
		mav.setViewName("shop/product/list");
		
		return mav; // WEB-INF/shop/product/list.jsp
	}
	
	// 상품 1건 상세보기
	@GetMapping("/product/detail")
	public ModelAndView getDetail(Model model, int product_idx) {
		
		ModelAndView mav = new ModelAndView();
		
//		List topList = topCategoryService.selectAll();
//		model.addAttribute("topList", topList);
		
		Product product = productService.select(product_idx); 
//		model.addAttribute("product", product);
		mav.addObject("product", product);
		
//		model.addAttribute("formatManager", formatManager);
		mav.addObject("formatManager", formatManager);
		
		mav.setViewName("/shop/product/detail");
		
		return mav;
	}
}
