package com.sds.mall.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sds.mall.domain.Member;
import com.sds.mall.exception.MemberException;
import com.sds.mall.model.member.MemberService;

// 회원과 관련된 요청을 전담하는 하위 컨트롤러
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	// 로그인 폼 요청 처리
	@GetMapping("/member/loginform")
	public ModelAndView getLoginForm() {
		ModelAndView mav = new ModelAndView("shop/member/login");
		return mav;
	}
	
	// 회원가입 폼 요청 처리
	@GetMapping("/member/registform")
	public ModelAndView getRegistForm() {
		ModelAndView mav = new ModelAndView("shop/member/regist");
		return mav;
	}
	
	@PostMapping("/member/regist")
	@ResponseBody // ViewResolver로 jsp 해석되지 않게 설정 
	public String regist(Member member) {
		// 스프링은 클라이언트의 파라미터명과 서버측 DTO의 멤버변수명이 일치할 경우 자동으로 매핑
//		System.out.println("uid= "+member.getUid());
//		System.out.println("pw= "+member.getMemberDetail().getPassword());
		
		memberService.regist(member); // 메서드 호출 후 예외 감지 시 @ExceptionHandler가 명시된 메서드 호출
		
		// 비동기 방식으로 요청했기에 응답 데이터는 jsp가 아님
		return "OK"; // 순수 데이터 반환
	}
	
	@PostMapping("/member/login")
	@ResponseBody
	public String login(Member member) {
		memberService.login(member);
		return "OK";
	}
	
	@ExceptionHandler(MemberException.class)
	@ResponseBody // 비동기 요청에 대한 에러 처리이기에 응답 정보 또한 순수 데이터임
	public String handle(MemberException e) {
		return "fail";
	}
}
