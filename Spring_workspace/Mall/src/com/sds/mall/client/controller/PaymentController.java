package com.sds.mall.client.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sds.mall.domain.Member;
import com.sds.mall.domain.OrderSummary;
import com.sds.mall.exception.OrderException;
import com.sds.mall.model.common.FormatManager;
import com.sds.mall.model.order.CartService;
import com.sds.mall.model.order.OrderService;

// 결제와 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class PaymentController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private FormatManager formatManager;
	
	@GetMapping("/order/payment/payform")
	public String getForm(HttpSession session, Model model) {
		
		Member member = (Member)session.getAttribute("member");
		
		List receiverList = orderService.selectReceiver(member);
		List paymethodList = orderService.selectPaymethod();
		List cartList = cartService.selectByMember(member); // 회원이 사용중인 장바구니. 결제완료 후 삭제 진행
		
		model.addAttribute("receiverList", receiverList);
		model.addAttribute("paymethodList", paymethodList);
		model.addAttribute("cartList", cartList);
		model.addAttribute("formatManager", formatManager);
		
		return "shop/order/checkout";
	}
	
	// 주문 요청
	@PostMapping("/order/payment/pay")
	public String regist(OrderSummary orderSummary, HttpSession session) {
//		System.out.println("total_buy is "+orderSummary.getTotal_buy());
//		System.out.println("total_pay is "+orderSummary.getTotal_pay());
//		System.out.println("receiver_idx is "+orderSummary.getReceiver().getReceiver_idx());
//		System.out.println("paymethod_idx is "+orderSummary.getPaymethod().getPaymethod_idx());
		
		// 현재 로그인한 회원의 정보를 orderSummary에 대입
		Member member = (Member)session.getAttribute("member");
		orderSummary.setMember(member);
		
		orderService.order(orderSummary);
		
		return "redirect:/shop";
	}
	
	@ExceptionHandler(OrderException.class)
	public ModelAndView handle(OrderException e) {
		ModelAndView mav = new ModelAndView("shop/error/result");
		mav.addObject("e", e);
		return mav;
	}
}
