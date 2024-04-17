package com.sds.mall.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sds.mall.model.product.TopCategoryService;

// 어플리케이션에서 전반적으로 사용되는 공통 코드가 발견된다면 일일이 코드로 중복작성하지 않고
// 하나의 관점(aspect)으로 정의하여 개발자가 원하는 시점에 동작시킨다
// DI는 결합도를 낮추나, AOP는 결합도를 없애는 것이 목적

// 아래 클래스는 쇼핑몰에서 언제나 보여줘야할 최상위 카테고리 조회를 모든 컨트롤러에서 중복 작성하지 않고
// AOP로 처리하여 모든 컨트롤러 작동 시점에 관여하도록 정의

public class TopCategoryAspect {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	// Spring의 AOP 의존성 jar 가져오기 (Aspectj weaver)
	public Object getTopCategoryList(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Object returnObj = null; // 원래 호출하려던 메서드 호출 후 반환되는 객체
		
		String targetName = joinPoint.getTarget().getClass().getName(); // 원래 호출하려던 객체
		System.out.println("원래 호출하려던 대상 클래스: "+targetName);
		
		Signature sig = joinPoint.getSignature(); // 원래 호출하려던 메서드
		System.out.println("원래 호출하려던 대상 메서드: "+sig.getName());
		
		// 쇼핑몰의 모든 요청에 대해 topCategory를 가져오는 것은 비효율적임
		// 필요한 경우만 선별해 구해오려면 요청의 uri를 파악하여 개발자가 원하는 경우에 요청: request 객체 필요
		HttpServletRequest request= ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String uri = request.getRequestURI(); //클라이언트의 URI 정보 
		
		if(
			// 회원 가입 요청은 비동기
			uri.equals("/member/regist") || uri.equals("/member/login")
		) { // topCategory가 필요 없다
			returnObj = joinPoint.proceed(); // 원래 호출하려던 하위 컨트롤러 메서드 호출
		} else { // topCategory가 필요한 경우
			// 하위 컨트롤러의 메서드가 호출되기 전에 TopCategory 목록을 여기서 구해놓기
			// 현재 요청에 의해 생성된 request 객체에 topList를 심어놓기
			// 컨트롤러의 topList 가져오는 업무를 대신 처리
			List topList = topCategoryService.selectAll();
			returnObj = joinPoint.proceed(); // 원래 호출하려던 하위 컨트롤러 메서드 호출
			// 만일 하위 컨트롤러에서 반환된 자료형이 ModelAndView인 경우 Model에 topList를 심어 놓자
			ModelAndView mav = null;			
			if(returnObj instanceof ModelAndView) {
				mav = (ModelAndView)returnObj;
				mav.addObject("topList", topList);
			}			
		}
		
		// 리턴값은 하위 컨트롤러가 반환한 값을 존중해야 한다
		// ModelAndView가 되거나, ResponseBody가 명시된 경우 일반 String
		// 따라서 joinPoint.proceed() 메서드 반환 값을 그대로 반환		
		return returnObj;
	}
	
}
