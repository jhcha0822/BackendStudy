package com.sds.project0304;

// 게임의 최상위 객체인 GameObject를 상속받아
// 부모 메서드의 move() 메서드를 오버라이딩
public class Hero extends GameObject{
	// 추상메서드를 상속받는 자식은 부모가 진 빚을 물려받는 느낌으로 개발
	// 부모가 완성하지 못하였던 해당 메서드를 자식세대에서 청산할 의무를 가짐
	public void move() {
		// 움직임 코드 작성
	}
	
	public static void main(String[] args) {
		Hero h = new Hero();
	}
}
