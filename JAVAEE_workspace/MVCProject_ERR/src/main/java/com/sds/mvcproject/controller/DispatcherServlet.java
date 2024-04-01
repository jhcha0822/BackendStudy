package com.sds.mvcproject.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 모든 요청을 1차적으로 처리하는 대표 컨트롤러

// javaEE 컨트롤러의 업무 처리 절차
// 1) 요청 수령(대표 컨트롤러)
// 2) 요청 분석(대표 컨트롤러->하위 컨트롤러)
// 3) 알맞은 모델에게 전달(하위 컨트롤러->모델)
// 4) 결과 View에 전달할 것이 있다면 결과 저장(하위 컨트롤러)
// 5) 결과를 보여줄 적절한 View 선택()
// 6) Tomcat(서버)이 응답

public class DispatcherServlet extends HttpServlet{

	// 실행중인 프로그램이 텍스트파일을 읽어야 하기에 파일입력스트림 사용
	FileInputStream fis = null;		
	Properties props = null; // 읽어들인 데이터를 대상으로 key/value를 구분할 수 있는 능력이 있는 자바의 객체 사용(java.util.Properties)
	
	// 서블릿이 요청을 분석할 때 필요한 파일이 이미 로드되어 있어야 하기에
	// 서블릿의 초기화를 담당하는 init에서 파일을 로드
	public void init(ServletConfig config) throws ServletException {
		// jsp에서의 어플리케이션 내장 객체는 서블릿 API에서 ServletContext라는 자료형에 보유
		ServletContext application = config.getServletContext();

		// web.xml의 init-param 태그에 명시된 파라미터명을 이용하여 그 값을 얻기
		// 서블릿이 탄생 시점에 호출되는 init() 생명주기 메서드
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String realPath = application.getRealPath(contextConfigLocation);
		// System.out.println("어플리케이션 내 지정한 디렉토리의 실제 경로: "+realPath);
		
		try {
			fis = new FileInputStream(realPath); // 스트림 생성
			props = new Properties(); // Properties 객체는 스트림 객체를 통해 파일의 정보 사용 가능
			props.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 요청을 받으려면 doXXX형 메소드를 재정의 해야 한다
	// 클라이언트가 post, get중 어느 요청을 시도할지 모르기 때문에 모든 요청 방식에 대비
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Handle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Handle(request, response);
	}
	
	protected void Handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수많은 클라이언트의 요청을 분석하는 도구로 if 조건문을 사용한다면
		// if문이 다량으로 나열되며, 요청이 추가되면 클래스를 열어 if문을 추가하고 컴파일까지 진행해야 하여 유지보수성이 떨어짐
		// java코드에서 요청을 처리할 하위 컨트롤러를 매핑하지 말고, 외부의 구조화된 설정 파일을 이용한다
		// (XML, JSON, Properties) 등의 텍스트 기반의 데이터베이스 파일은 조건문을 대신할 수 있다.
		
		// 클라이언트의 요청을 검색어로 사용하여 매핑파일에서 key값을 이용해 value 추출
		String uri = request.getRequestURI();
		System.out.println("클라이언트의 요청 uri: "+uri);
		
		String value = props.getProperty(uri); // uri를 key로 하여 value 추출
		System.out.println("uri와 매칭되는 value: "+value);
		
		// value로 반환된 하위 컨트롤러의 경로는 실제 클래스가 아닌 단순 문자열이기에
		// 실제 클래스를 Load 및 인스턴스 생성이 필요
		try {
			Class controllerClass = Class.forName(value); // 문자열로 지정한 클래스를 static에 로드
			// Class 클래스에는 인스턴스를 생성시켜주는 메서드를 지원
			Controller controller = (Controller)controllerClass.newInstance(); // 다형성을 이용한 처리
			controller.execute(request, response);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}	
	
	// 서블릿이 소멸될 때 생성된 스트림도 함께 제거
	public void destroy() {
		if(fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
