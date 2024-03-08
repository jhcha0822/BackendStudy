package com.sds.project0308.bar;

import javax.swing.JProgressBar;

// 재사용성이 있기에 인스턴스로 생성
// 바의 값을 증가시킬 스레드의 정의
public class BarThread extends Thread{
	JProgressBar bar;
	int n = 0;
	int step; // 증가시킬 값
	boolean flag = true;
	
	public BarThread(JProgressBar bar, int step) {
		this.bar = bar;
		this.step = step;
	}
	
	public void download() {
		n += step;
		bar.setValue(n);
		if(n >= 100) {
			n = 100;
			flag = false;
		}
	}
	
	public void run() {
		while(flag) {
			download();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
