package com.sds.project0304;

// implements: 객체를 상속받는 것이 아니라 기능을 상속받게 됨 --> 클래스 간 다중상속을 피할 수 있음
// interface는 같은 동작으로 인정. is-a 관계 --> 형 변환이 가능
public class MP3Player extends MusicPlayer implements Board, ZetFly{
	String brand;
	String productName;
	
	public void showEqualizer() { // 이퀄라이저 시각화
		
	}
	
	public void setVolume() { // 볼륨 조절
		System.out.println("볼륨 조절");
	}
	
	// @(어노테이션): 주석, Java5부터 적용. 실행 시 해석됨
	// Spring에서 많이 사용됨
	@Override
	public void playMP3File() {
		// TODO Auto-generated method stub
		System.out.println("MP3 파일 재생");
	}
	
	// Board의 기능을 오버라이딩
	@Override
	public void roll() {
		// TODO Auto-generated method stub
		System.out.println("음향기기를 타고 다닐 수도 있다더라");
	}
	
	@Override
	public void fly() {
		// TODO Auto-generated method stub
		System.out.println("심지어 날 수도 있음 엌ㅋㅋ");
	}
}
