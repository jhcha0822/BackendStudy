package com.sds.spring.exception;


// 아래의 클래스는 런타임 exception을 상속받는 나만의 커스터마이징된 예외 객체
// 강요된 예외: sun에서 아주 심각한 예외는 이미 컴파일 타임에 예외처리를 강요하는 예외
// 런타임: 컴파일 타임에 강요하지 않는 예외로 처리 여부는 개발자의 몫
public class BoardDMLException extends RuntimeException{
	
	public BoardDMLException(String msg) {
		super(msg); // 부모 생성자에 에러 메시지를 심을 수 있는 생성자가 지원됨
	}
	
	public BoardDMLException(String msg, Throwable e) {
		super(msg,e); // 에러 원인이 되는 객체 e 제공
	}
	
}
