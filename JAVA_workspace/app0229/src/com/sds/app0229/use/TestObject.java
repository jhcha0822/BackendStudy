// 자바에서는 개발자가 아무런 상속관계를 명시하지 않아도
// 기본적으로 무조건 상속되는 최상위 객체인 Object가 존재한다

package com.sds.app0229.use;

// Object를 import하지 않아도 java.lang은 기본 import되어 있어 문제 X

class TestObject /* extends Object*/ { /* TestObject is a Object */
	public static void main(String[] args) {
		
		// 아래의 3 레퍼런스 변수 모두 자식인 TestObject의 인스턴스 주소를 가리킨다
		// 상속관계에서 부모의 인스턴스 주소값은 메모리에 올라간 방법이 없기에 주소를 가져올 수 없다

		// TestObject to = new TestObject(); // A 가능
		Object obj = new TestObject();    // B 가능
		// System.out.println("Hello World!");

		TestObject re = (TestObject)obj; // C

		// A: 자식 인스턴스 + 부모 멤버
		// B: 자식의 인스턴스 내 부모 멤버 (예외. 자식이 오버라이드한 메서드가 있으면 그 메서드 호출)
		// C: 
	}
}
