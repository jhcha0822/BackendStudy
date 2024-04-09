package com.sds.mall.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sds.mall.domain.Color;
import com.sds.mall.domain.Product;
import com.sds.mall.domain.Psize;
import com.sds.mall.model.product.ProductService;
import com.sds.mall.model.product.TopCategoryService;

// 관리자 모드에서 상품 관련 요청 처리 하위 컨트롤러
@Controller
public class ProductController {
	
	// 부장님에게 일 시키기
	@Autowired
	private TopCategoryService topCategoryService;
	
	@Autowired
	private ProductService productService;
	
	// 상품 등록 폼 요청 처리: Getmapping 요청 시 아래 함수 수행
	@GetMapping("/admin/product/registform")
	public String getRegistForm(Model model) {
		// 3) model에 전달
		List topList = topCategoryService.selectAll();
		
		// 4) 결과 저장
		model.addAttribute("topList", topList);
		
		return "admin/product/regist";
	}
	
	// 상품 업로드 요청 쿼리(파일 포함)
	// Post 방식으로 요청되어 옴
	@PostMapping("/admin/product/regist") 
	public String regist(Product product, String[] color_name, String[] size_name) {
	
		System.out.println("하위 카테고리 "+product.getSubCategory().getSubcategory_idx());
		System.out.println("상품명 "+product.getProduct_name());
		System.out.println("브랜드 "+product.getBrand());
		System.out.println("가격 "+product.getPrice());
		System.out.println("상세설명 "+product.getDetail());
		
		System.out.println("색상 수는 "+color_name.length);
		System.out.println("사이즈 수는 "+size_name.length);
		
		// 서비스에게 Product DTO 전달 이전 넘어온 색상 및 사이즈 배열을 DTO에게 전달해 한꺼번에 입력
		List<Color> colorList = new ArrayList();
		for(String s : color_name) { // color_name 은 String이기에 DTO로 처리해줘야 함
			Color color = new Color(); // empty DTO
			color.setColor_name(s);
		}
		
		List<Psize> psizeList = new ArrayList();
		for(String s : size_name) { // color_name 은 String이기에 DTO로 처리해줘야 함
			Psize psize = new Psize(); // empty DTO
			psize.setSize_name(s);
		}
		
		product.setColorList(colorList);
		product.setPsizeList(psizeList);
		
		
		productService.regist(product); // 컨트롤러는 서비스에게 업무를 추상적으로 전달
		
		return "redirect:/admin/product/list";
	}
}
