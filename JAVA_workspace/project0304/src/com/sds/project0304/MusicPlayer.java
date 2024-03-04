package com.sds.project0304;

// 최상위 클래스 정의
public abstract class MusicPlayer {
	int vol;
	
	// 상속받는 자식은 오버라이딩하여 사용할 것
	public abstract void setVolume();
	
	public abstract void playMP3File();
}
