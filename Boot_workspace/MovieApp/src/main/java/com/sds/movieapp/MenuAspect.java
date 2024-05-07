package com.sds.movieapp;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sds.movieapp.model.movie.MovieService;

import jakarta.servlet.http.HttpServletRequest;

// Spring MVC에서는 xml 파일에서 bean 설정을 하였으나
// Spring Boot에서는 @Configuration 클래스에서 담당
// 아래의 클래스를 aspect로 등록하여 aop 구현
@Aspect
@Component //메모리에 올리기
public class MenuAspect {
	
	@Autowired
	private MovieService movieService;

	// 동작 지점(pointcut) 및 시점(arround) 설정
	
	// 일반 컨트롤러에 대해 AOP 적용
	@Pointcut("execution(public * com.sds.movieapp.controller..*(..))")
	public void includeExecution() {} // pointcut을 쓰기 위한 메서드. 로직을 작성하지 않음
	
	// 아래 패턴(RestController)은 제외
	@Pointcut("!execution(public * com.sds.movieapp.controller.Rest*Controller.*(..))")
	public void excludeExecution() {}

	@Around("includeExecution() && excludeExecution()")
	public Object getMenu(ProceedingJoinPoint joinPoint) throws Throwable {
		// 하위 컨트롤러의 메서드는 String 혹은 ModelAndView를 반환 -> Object로 받기
		Object obj = joinPoint.proceed(); // 대표 컨트롤러가 호출하려던 하위 컨트롤러의 메서드 호출
	
		// 공통 코드 명시
		List movieTypeList = movieService.getMovieTypeList();

		// request 객체에 담기
		// 이 요청과 관련된 request 객체를 꺼내기 
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		request.setAttribute("movieTypeList", movieTypeList);
		
		return obj;
	}
	
	
	
}
