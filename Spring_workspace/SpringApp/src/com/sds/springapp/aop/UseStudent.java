package com.sds.springapp.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseStudent {

	public static void main(String[] args) {
		// Student student = new Student();
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/sds/springapp/spring/context.xml");
		
		Student student = (Student)context.getBean("student"); // 컨테이너에서 가져오기
		
		student.gotoSchool();
		student.study();
		student.haveLunch();
		student.goHome();
		
	}

}
