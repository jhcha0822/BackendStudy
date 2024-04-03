package com.sds.springapp.di;

// 요리사
public class Cook {
	
	// has-a
	// FriPan pan; 	// FriPan 객체를 지우게 된다면 아래에서 에러 발생
					// 즉, FriPan 객체에 결합도가 높다 (의존성이 강하다)
					// 상위 객체를 이용하여 보다 유연하게 설정
					// 인터페이스를 주로 사용(다형성)
	
	private Pan pan;
	
	// 주입 시 setter나 생성자 메서드 필요
	public void setPan(Pan pan) {
		this.pan = pan;
	}
	
	public Cook() { // Constructor
		// has-a 관계에서 부품이 되는 객체를 상위자료형으로 선언한다 할지라도
		// pan = new FriPan(); 	// new 연산자는 정확한 하위 자료형이 필요하기에 DI가 완성되지 못함
							   	// 따라서 Spring에서는 주로 new 하지 않고 Spring이 Bean으로 메모리에 올려 사용
								// 이를 Spring이 넣어주는 것을 주입(Injection)으로 부른다
		
		// DI (Dependency Injection: 의존성 주입X, 의존성 있는 객체를 외부에서 주입하여 결합도를 낮춘다)
		// spring 패키지의 context.xml에서 접근
		
		
	}
	
	public void makeFood() {
		// 클래스를 지워도 makeFood()는 영향을 받지 않는다
		pan.boil();
	}
	
}
