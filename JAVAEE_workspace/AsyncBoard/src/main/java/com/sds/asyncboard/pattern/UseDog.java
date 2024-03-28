package com.sds.asyncboard.pattern;

// 외부에서 Dog 사용

public class UseDog {

	public static void main(String[] args) {
		// Dog d1 = new Dog(); // OK
		// Dog d2 = new Dog(); // 주소값이 다른 인스턴스 생성
		
		Dog d1 = Dog.getInstance();
		Dog d2 = Dog.getInstance();
		
		System.out.println(d1);
		System.out.println(d2);
	}

}
