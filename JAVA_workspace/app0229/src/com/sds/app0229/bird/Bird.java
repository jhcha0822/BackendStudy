
package com.sds.app0229.bird;

public class Bird {
	public String name = "그냥 새";
	public int age = 23;

	/*
	public Bird(){
		super(); // 최상위 객체 object
	}
	*/

	public void eat(){
		System.out.println("먹이를 먹었다.");
	}

	public void fly(){
		System.out.println("부모 새가 날았다.");
	}
}
