package com.sds.mall.client.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.mall.domain.Cart;
import com.sds.mall.domain.Member;
import com.sds.mall.exception.CartException;
import com.sds.mall.model.order.CartService;

// 비동기 요청에 대한 처리만을 담당
// 이 컨트롤러에서의 모든 응답은 jsp가 아닌 순수 데이터(jsp, xml, text..)

// RestController: 모든 메서드에는 @ResponseBody가 생략되어 있음
// 일반 Controller: 
@RestController
public class RestCartController {
	
	@Autowired
	private CartService cartService;
	
	// 장바구니 등록 (비동기)
	@PostMapping("/order/cart/regist")
	//@ResponseBody // 순수 데이터 반환, jsp로 resolve X
	public ResponseEntity regist(Cart cart, HttpSession session) {
		// 전송 정보 확인
		System.out.println("무엇을 "+cart.getProduct().getProduct_idx());
		System.out.println("몇 개 "+cart.getEa());
		Member member = (Member)session.getAttribute("member"); // MemberController에서 반환
		cart.setMember(member);
		System.out.println("누가 "+cart.getMember().getMember_idx());
		
		// 3. 업무 전달
		cartService.regist(cart);
		
		// Http 프로토콜에 맞게 응답 정보 구성(머리, 몸체, 상태코드 등)
		// 클라이언트가 서버의 상황을 정확히 알 수 있다
		ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).build();
		
		return entity;
	}
	
	// 장바구니 관련 에러 처리
	@ExceptionHandler(CartException.class)
	public ResponseEntity handle() {
		ResponseEntity entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return entity;
	}
	
}
