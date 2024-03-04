package com.sds.project0304;

// 추상 클래스를 하나라도 가지고 있다면 클래스는 완전하지 않으므로 추상클래스가 된다.
// 따라서 클래스 선언부에도 abstract를 명시해야 한다.
// 추상 클래스는 불완전하기에 new 연산자로 직접 인스턴스를 생성할 수 없다
// 추상 클래스를 메모리에 올리려면 자식을 new하여 추상메서드의 변수와 메서드가 메모리에 올라와 사용 가능함
public  abstract class GameObject {
	int x = 100;
	int y = 200;
	
	// 이클립스는 실시간 컴파일러이기에 컴파일 결과를 소스 저장 시 실시간으로 알려줌
	// 컴파일 에러 발생 시 아래와 같이 빨간 줄이 생김
	
	// 개발자가 의도하여 몸체없는 메서드인 추상 메서드를 정의
	// 메서드 선언부에, 추상적이라는 의미인 abstract 수식자(modifier)를 붙여준다
	public abstract void move();
	
	public void msg() {
		// ctrl + space : 자동완성 (syso)
		System.out.println(x + ", " + y);
	}
}
