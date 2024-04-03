package com.sds.springapp.aop;

// 학생
public class Student {

	/*
	
	// Bell bell = new Bell();
	private Instrument bell; 	// 결합도를 낮추기 위해 상위 자료형 보유
								// new 하지 않고 setter or 생성자 메서드 이용
	
	public void setBell(Instrument bell) { // 외부에서 DI로 주입
		this.bell = bell; 		// context에서 name은 bell이 된다
	}
	
	*/ // 중복된 코드를 제거하였기에 아예 결합을 없앤다
	
	public void gotoSchool() {
		System.out.println("학교에 간다");
		// bell.sound(); 중복된 코드 제거
	}
	
	public void study() {
		System.out.println("공부한다");
		// bell.sound();
	}
	
	public void haveLunch() {
		System.out.println("점심을 먹는다");
		// bell.sound();
	}
	
	public void goHome() {
		System.out.println("귀가한다");
		// bell.sound();
	}
	
	
}
