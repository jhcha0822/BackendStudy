package com.sds.mall.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.sds.mall.exception.UnAuthorizedException;

// 어떤 특정한 컨트롤러에서의 예외가 아닌,
// 어플리케이션에서 발생하는 전역적인 예외를 처리할 때 @ControllerAdvice 사용 
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UnAuthorizedException.class)
	public Object handle(UnAuthorizedException e) {
		
		ModelAndView mav = new ModelAndView("shop/error/result");
		mav.addObject("e", e);
		
		return mav;
	}
	
}
