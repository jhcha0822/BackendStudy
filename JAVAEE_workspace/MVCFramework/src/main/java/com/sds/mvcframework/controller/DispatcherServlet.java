package com.sds.mvcframework.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcframework.blood.controller.BloodController;
import com.sds.mvcframework.movie.controller.MovieController;

// model2 방식 기반으로 어플리케이션을 만들면
// 모든 클라이언트의 요청마다 1:1 대응하는 컨트롤러를 매핑
// web.xml의 매핑 관리가 힘들어져 유지보수성이 오히려 떨어짐

// 기존의 컨트롤러보다 앞선 위치에 모든 요청을 하나의 진입점으로 몰아넣을 수 있는 대표 컨트롤러 생성
// ex) 대기업 서비스센터의 업무와 비슷함

// 이 클래스는 대형 어플리케이션의 모든 요청을 혼자 받아야 하기에
// 어떤 하위 컨트롤러가 어떤 업무를 담당해야 할 지를 구분해낼 수 있는 로직이 있어야 함
// Spring의 창시자인 로드 존슨이 Spring MVC에서 객체명으로 DispatcherServlet 사용

public class DispatcherServlet extends HttpServlet{
	// 어떠한 분야의 프로그램을 개발해도 이 세상의 모든 컨트롤러는 아래의 순서로 요청을 처리한다
	// 1) 요청을 수령
	// 2) 요청을 분석
	// 3) 알맞는 로직 객체(모델)에게 업무 전달
	// 4) 결과를 보여줄 것이 있다면 저장
	// 5) 결과 페이지
	
	Properties props;
	FileInputStream fis; // properties 객체는 file에 접근하는 능력이 없기에 스트림 객체에 의존
	
	// if문을 제거하기 위해 if문을 대신할 수 있는 외부의 설정 파일을 이용해본다
	public void init(ServletConfig config) throws ServletException { // 매개변수로 전달되는 ServletConfig 객체는 서블릿과 관련된 환경 정보를 가진 객체이다
		props = new Properties(); // 어떤 문자열 데이터가 key=value로 구성되어 있다면 key를 이용하여 value에게 접근 가능한 객체
		try {
			String value = config.getInitParameter("contextConfigLocation"); // web.xml에 명시된 param 이름으로 파일 접근
			ServletContext context = config.getServletContext(); // ServletConfig 객체는 application 내장객체의 자료형인 ServletContext를 얻을 수 있다
			String fullPath = context.getRealPath(value); // "D:/MULTICAMPUS/JAVAEE_workspace/MVCFramework/src/main/webapp"+value
			// 플랫폼 환경에 따른 전체경로를 조사
			fis = new FileInputStream(fullPath);
			props.load(fis); // props 객체가 파일의 내용을 인식
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// 더블코딩 방지 doRequest()
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	// 1) 요청을 수령
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 2) 요청을 분석(클라이언트의 요청을 파악하여 적절한 하위 컨트롤러에게 업무 전달)
		// System.out.println("요청 감지");
		
		// 클라이언트가 무엇을 원하는지 파악하려면, 클라이언트가 요청 시 사용한 URL의 URI 분석
		// URL: localhost:9999/movie.do URI: /movie.do
		String uri = request.getRequestURI();
		// System.out.println(uri);
		
		// properties 객체로 if문 대체
		String className = props.getProperty(uri);
		System.out.println(uri+" 요청에 동작을 수행할 하위 컨트롤러는 "+className);
		
		// className 변수는 실제 클래스가 아니라 클래스의 경로를 나타내는 문자열일 뿐이므로 인스턴스화 불가능
		// Class.forName() 메서드를 이용하면 메서드의 매개변수로 개발자가 static 영역으로 클래스를 load 가능
		try {
			Class controllerClass = Class.forName(className.trim()); // 동적 load
			// static 영역에 올리는 것만으로는 하위 컨트롤러를 동작시킬 수 없다.
			// 하위 컨트롤러가 보유한 메서드가 인스턴스 메서드이기 때문에 힙 영역으로 올려야 한다
			Controller obj = (Controller)controllerClass.newInstance(); // new 연산자를 호출하는 효과. 인스턴스 생성, object를 controller로 형변환
			obj.execute(request, response); // 상위 객체이기에 ---Controller로 형변환할 필요 없다. 다형성
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		if(uri.equals("/blood.do")) {
			BloodController controller = new BloodController();
			controller.execute(request, response);
		}
		else if(uri.equals("/movie.do")) {
			MovieController controller = new MovieController();
			controller.execute(request, response);
		}
		
			
	}
	
	
}
