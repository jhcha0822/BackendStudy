package com.sds.mall.model.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.catalina.core.ApplicationContext;

// Tomcat 서버가 가동될 때 무언가 하고 싶은 작업이 있을 경우,
// 이 Listener를 통해 타이밍을 잡아 원하는 코드 사용
public class MyListener implements ServletContextListener {

	// Spring Container
	ApplicationContext context;
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) { // Tomcat 종료
		System.out.println("Tomcat 종료, Application 종료");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) { // Tomcat 가동 후 어플리케이션이 초기화될 때 호출
		System.out.println("Tomcat 가동, Application 시작");
		// sce를 이용하여 Application 내장 객체인 ServletContext를 획득
		ServletContext context = sce.getServletContext();
		String path = context.getInitParameter("contextConfigLocation");
		System.out.println("xml을 Spring 객체가 읽음"+path);
	}

}
