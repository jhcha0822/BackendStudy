package com.sds.project0304;

// 인터페이스: 완벽한 객체가 아닌, 메서드만을 보유한 객체
// ex) 새: 완전한 새가 아닌 날아다니는 행위만을 보유
// 완전한 객체가 아니기에 다중상속을 피할 수 있음

// interface는 추상메서드만을 보유하기에 abstract 사용할 필요가 없음
public interface Board {
	public abstract void roll();
}
