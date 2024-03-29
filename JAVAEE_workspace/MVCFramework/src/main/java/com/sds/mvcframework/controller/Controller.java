package com.sds.mvcframework.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 컨트롤러의 최상위 객체는 인터페이스로 정의
// 인터페이스를 상속받는 모든 자식들이 반드시 구현해야 할 메서드를 강제하기 위해
// 개발의 다형성과 일관성

public interface Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException;
}
