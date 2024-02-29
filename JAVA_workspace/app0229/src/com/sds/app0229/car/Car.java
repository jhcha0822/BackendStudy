
package com.sds.app0229.car;

public class Car {
	// 객체자료형도 자료형이므로, 멤버변수로 선언 가능
	// 멤버변수가 객체자료형이면 has a 관계이다
	public Wheel[] wheel;
	public Handle handle;
	public Door[] door;
	int price = 10000000; // 객체가 아니기에 has a 관계가 아님

	// 객체를 보유한 클래스의 인스턴스를 올릴 때는 초기화 작업이 일반 자료형에 비해 많다
	// 따라서 적극적 생성자 활용이 필요함
	public Car(){
		wheel = new Wheel[4]; // 배열 생성, 크기 명시
		wheel[0] = new Wheel();
		wheel[1] = new Wheel();
		wheel[2] = new Wheel();
		wheel[3] = new Wheel();

		handle = new Handle();

		door = new Door[4];
		door[0] = new Door();
		door[1] = new Door();
		door[2] = new Door();
		door[3] = new Door();
	}
}
