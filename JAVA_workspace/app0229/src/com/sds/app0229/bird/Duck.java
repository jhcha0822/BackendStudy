
package com.sds.app0229.bird;

// 새의 자식인 오리의 정의
public class Duck extends Bird {
	public String color = "white";

	/*
	public Duck(){
		super();
	}
	*/

	public void quack(){
		System.out.println("오리는 꽥꽥.");
	}

	public void fly(){
		System.out.println("오리도 난다.");
	}
}
