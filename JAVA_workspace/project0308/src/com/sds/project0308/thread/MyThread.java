package com.sds.project0308.thread;


// 하나의 프로세스 내에서 독립적으로 실행시키고 싶은 단위인 쓰레드를 나만의 로직으로 재정의

public class MyThread extends Thread {
	
	int count;
	String msg;
	
	public MyThread(String msg) {
		this.msg = msg;
	}
	
	// 개발자는 독립적으로 실행시키고 싶은 로직이 있다면 run() 메서드에 작성해야 함
	// 개발자가 정의해두면 run() 메서드 호출은 JVM이 알아서 진행
	@Override
	public void run() {
		while(true) {
			count++;
			System.out.println(msg);
			
			try {
				Thread.sleep(1000); // 1초간 non-runnable 영역으로 이동했다 다시 복귀
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}
