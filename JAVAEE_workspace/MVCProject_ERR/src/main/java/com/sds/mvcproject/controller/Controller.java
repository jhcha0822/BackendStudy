package com.sds.mvcproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 인터페이스로 Controller 최상위 객체 생성
// 추상클래스는 다중 상속이 불가하여 인터페이스의 효용이 높음
// Dispatcher 서블릿에서 형변환을 가능하게 하고,
// 구현강제를 시키기 위하여

public interface Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
