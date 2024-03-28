package com.sds.asyncboard.pattern;

// 아래의 클래스를 싱글턴(SingleTon) 패턴으로 정의하여 외부의 어떤 클래스가 인스턴스를 1개 이상 생성하지 않도록 방어해보기

public class Dog {
	String name = "뭉치";
	
	// class가 생성자에 대해 접근 제한을 가했으므로
	// 인스턴스를 제공할 의무가 class에게 넘어옴
	// 클래스 자료형을 제공하는 코드를 추가
	private static Dog instance; // 인스턴스 변수, new 되어야 사용 가능 -> static 변수
	
	// 외부에서 인스턴스 생성이 불가하기에, class가 직접 인스턴스 생성
	public static Dog getInstance() { // main인 static에서 사용가능하게 static 설정
		// static 선언된 instance가 존재하지 않을 때만 new 하기
		if(instance == null)
			instance = new Dog();
		return instance;
	}
	
	// 생성자에 접근제한을 둔다면 아무도 생성자를 호출 불가능
	// 1개는 생성되어야 하므로 보완이 필요 
	private Dog() {	}
}
