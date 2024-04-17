package com.sds.mall.exception;

// 자바에서는 실행중인 프로그램의 정상 수행을 방해하는 요인을 예외라 한다
// 예외 유형
// 1) 강요된 예외: SUN 사에서 정의한, 중대하다 판단되는 예외
//			      컴파일 타임(코드 작성 시) 예외를 처리하지 않으면 컴파일이 되지 않음
// 2) 강요되지 않은 예외: 나머지 예외. RuntimeException을 상속받아 개발자가 재정의 가능
public class MemberException extends RuntimeException {
	// 개발자가 원하는 메시지나 에러의 원인을 이 객체에 담는다
	// 이 정보들을 담으려면 RuntimeException의 생성자를 이용
	// 부모의 생성자 중 원하는 생성자를 호출(자바에서는 자식의 생성 이전 부모의 생성을 강제한다)
	
	public MemberException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	public MemberException(Throwable e) {
		// TODO Auto-generated constructor stub
		super(e);
	}
	
	public MemberException(String msg, Throwable e) {
		// TODO Auto-generated constructor stub
		super(msg, e);
	}
}
