package com.sds.project0308.sprite;

// 현재 프로그램 내에서 독립적으로 실행될 수 있는 세부 실행단위인 스레드 정의
public class MyThread extends Thread{

	Animation ani;
	
	public MyThread(Animation ani) {
		this.ani = ani;
	}
			
	// 독립적으로 실행시키고 싶은 코드가 있을때, run()에 작성
	public void run() {
		while(true) {
			// Animation 클래스가 보유한 move() 메서드 호출
			try {
				Thread.sleep(30);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ani.move();
		}
	}
}
