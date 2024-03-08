package com.sds.project0308.thread;


// Multitasking
// OS의 다중 프로세스 실행 기법
// CPU는 하나인 경우, 동시에 여러 프로그램을 가동하는 것은 불가능
// 따라서 시간을 분할하여 빠른 동작으로 번갈아가며 실행
// 이 경우 사용자는 동시 실행으로 느끼게 됨
// OS차원의 멀티태스킹 기법을 모방한 자바의 멀티쓰레딩 기법이 존재

// Thread
// 하나의 프로세스 내에서 독립적으로 실행될 수 있는 또 하나의 세부 단위
// 상속받아서 코드를 다시 작성해야 함
// 하나의 프로세스 내에서의 또 다른 작은 단위의 프로세스로 보자

public class ThreadTest {
	MyThread thread;
	MyThread thread2;
	public ThreadTest() {
		// 쓰레드 생성
		thread = new MyThread("★");
		thread2 = new MyThread("☆");
		//thread.run();    // 개발자가 쓰레드의 run()메서드를 직접 호출하게되면
		                        // JVM에 의한 관리방법이 아니기에 시분할 등이 불가해지고 일반 메서드 호출로 실행됨
		thread.start(); 	// 따라서 run()을 호출하지말고 JVM에게 맡겨야 함
		thread2.start();
		
	}
	
	public static void main(String[] args) {
		new ThreadTest();
	}

}
