package com.sds.springapp.di;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseCook {

	public static void main(String[] args) {

		// Spring의 설정 파일 context.xml을 읽어 해석할 한 후,
		// 이 빈 객체를 관리해주는 Spring의 컨테이너를 ApplicationContext 라 한다
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/sds/springapp/spring/context.xml");
		
		// Cook cook = new Cook();
		// cook.makeFood();
		
		// Spring Container가 보유한 Bean을 얻자
		Cook cook = (Cook)context.getBean("cook"); // 스프링 컨테이너가 보유한 빈 가져오기
		cook.makeFood();
	}

}
