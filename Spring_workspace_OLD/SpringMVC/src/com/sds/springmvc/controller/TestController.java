package com.sds.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 테스트 요청을 처리하는 하위 컨트롤러
// spring 2.xx 버전대에서는 모든 컨트롤러는 Controller 인터페이스를 상속받아야 하였으나
// 그 이후부터는 POJO를 추구하는 정책으로 더 이상 Controller를 강요하지 않고 선택사항으로 만들어두었다
public class TestController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//System.out.println("TestController의 handleRequest() 메서드 호출");
		
		// Model은 저장의 역할을 수행. View는 보여줄 페이지의 이름을 가질 수 있음.
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "테스트 결과입니다"); // request.setAttribute("msg", "테스트 결과입니다"); 와 동일
		mav.setViewName("board/result"); // view의 이름만을 반환 (접두어, 접미어는 context에서)
		
		// DispatcherServlet에게 반환됨
		// 이때 DispatcherServlet은 InternalResourceViewResolver에게 url해석을 맡긴다
		// 이 객체는 접두어+접미어 조합을 통해 jsp의 full 경로를 mav에 저장해 DispatcherServlet에게 전달함
		
		return mav;
	}

}
