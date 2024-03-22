package com.sds.newsapp.news.async;

// Thread:
// 하나의 프로세스 내에서 독립적으로 실행되는 세부 실행 단위

public class UseThread {

	public static void main(String[] args) {
		/*
		MyThread t = new MyThread();
		t.start();
		System.out.println("B"); // 메인 실행부 수행완료
		*/
		
		// 순서에 상관없이 쓰레드 두개가 동시 실행된다
		// 이를 비동기 방식이라 한다

		// JAVA는 쓰레드 기반의 언어이므로 메인 실행부조차 스레드이다
		int[] arr = new int[3];
		arr[0] = 5;
		arr[1] = 8;
		arr[2] = 9;
		arr[3] = 1; // 컴파일 시점에 못잡고 실행시 에러
	}
}
