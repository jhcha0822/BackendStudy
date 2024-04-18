package com.sds.mall.client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sds.mall.domain.Cart;
import com.sds.mall.domain.Member;
import com.sds.mall.domain.Product;
import com.sds.mall.model.common.FormatManager;
import com.sds.mall.model.order.CartService;
import com.sds.mall.exception.CartException;

// 장바구니 관련 요청 처리
@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private FormatManager formatManager;
	
	// 장바구니 목록
	@GetMapping("/order/cart/list")
	public String getList(HttpSession session, Model model) {
		Member member = (Member)session.getAttribute("member"); // 세션으로 현재 회원 얻어오기
		List cartList = cartService.selectByMember(member);
		model.addAttribute("cartList", cartList);
		model.addAttribute("formatManager", formatManager);
		return "shop/order/cart";
	}
	
	// 장바구니 수정 요청 처리
	@PostMapping("/order/cart/update")
	public String update(int[] product_idx, int[] ea, HttpSession session) {
		// System.out.println("ea 배열의 길이: "+ea.length);
		Member member = (Member)session.getAttribute("member");
		List cartList = new ArrayList();
		for(int i=0; i<product_idx.length; i++) {
			Cart cart = new Cart();
			cart.setEa(ea[i]);
			cart.setMember(member);
			Product product = new Product();
			product.setProduct_idx(product_idx[i]);
			cart.setProduct(product);
			cartList.add(cart);
		}
		cartService.updateGroup(cartList);
		return "redirect:/order/cart/list";
	}
	
	// 장바구니 삭제
	@GetMapping("/order/cart/delete")
	public String delCart(Cart cart) {
		//System.out.println(cart.getCart_idx());
		cartService.delete(cart);
		return "redirect:/order/cart/list";
	}
	
	// 장바구니 관련 에러 처리
	@ExceptionHandler(CartException.class)
	public ModelAndView handle(CartException e) {
		ModelAndView mav = new ModelAndView("shop/error/result");
		mav.addObject("e", e);
		return mav;
	}
}
