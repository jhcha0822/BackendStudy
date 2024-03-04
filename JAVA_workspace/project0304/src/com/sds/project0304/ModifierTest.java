package com.sds.project0304;

/*
 static: 변수, 메서드등을 클래스 원본에 고정시키는 수식자
 abstract: 추상 클래스, 추상 메서드 선언 시 사용
 final: const와 동일. final로 선언한 변수는 변경 불가
 */

// 자바에서 public static final의 의미는 중요함

public final class ModifierTest { // 오버라이딩 불가
	public static void main(String[] args) {
		final int x=5; // 이 시점부터는 변경 불가
		// x=8; // 컴파일 에러
	}
}
