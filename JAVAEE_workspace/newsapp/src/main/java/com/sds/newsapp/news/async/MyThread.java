package com.sds.newsapp.news.async;

// 이 클래스는 독립적으로 실행순서 없이 독자적으로 실행되는 단위가 됨
// 

public class MyThread extends Thread {
	
	@Override
	public void run() {
		for(int i=0; i<10; i++)
			System.out.println("A"); // 쓰레드의 run() 수행중
	}
}
