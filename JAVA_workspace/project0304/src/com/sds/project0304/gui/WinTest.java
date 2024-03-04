package com.sds.project0304.gui;

// 빠르게 import: ctrl + shift + o
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;

/*
 너비 500, 높이 600
 자바의 gui 객체들이 모여 있는 패키지는 java.awt
*/


public class WinTest {
	public static void main(String[] args) {
		// 자바 gui에서 윈도우 객체는 최상위 객체이므로 직접 사용하지 않음
		// 하위 객체인 Frame 객체를 사용
		Frame frame = null; // 객체의 인스턴스가 존재하지 않는 상태
		
		// SUN 또는 남이 제공해준 클래스를 사용하는 방법 (API 빨리 읽기)
		// 현재 클래스의 사용목적 파악
		// ex) Frame: 윈도우를 표현할 클래스
		// 메모리에 올리기 위해 3가지 유형중 어느 것에 속하는 지 조사
		// 1) 일반클래스 - new 연산자로 직접 올리기
		// 2) 추상클래스 - new로 직접 올릴 수 없음
		// 2-1) 자식 클래스를 정의하여 자식을 new 하기
		// 2-2) SUN에서 별도로 제공해주는 방법이 있을 경우 사용
		// 3) 인터페이스 - 2와 동일
		
		// 빠르게 API 읽기 -> 커서 올리고 shift + F2
		
		// 프레임을 메모리에 올리기
		// 일반 클래스이므로 new 이용
		frame = new Frame();
		frame.setVisible(true);
		frame.setSize(500, 600);
		
		// 프레임위에 각종 컴포넌트 삽입
		Button bt = new Button("New Button"); // 클래스를 메모리에 로딩
		
		// 윈도우창에 버튼 부착
		// 모든 GUI 프로그램에서는 컨테이너인 부모에 자식요소를 붙일 때
		// 어떤 방식으로 붙일 지 배치방법(레이아웃)을 반드시 명시해야 함
		// 개발자가 레이아웃 스타일을 지정하지 않으면 default로 적용
		// 현재 BorderLayout이라는 배치 방법이 적용되어 버튼이 와방 크게 등장
		// 현재 배치 방법을 배우지 않았으므로 FlowLayout 적용
		frame.setLayout(new FlowLayout());
		
		// html에서의 텍스트박스 - java에서는 testfield
		TextField t1 = new TextField(15);
		
		// html에서의 selectbox - java에서의 choice
		Choice ch = new Choice();
		ch.add("JAVA");
		ch.add("Oracle");
		ch.add("JSP");
		ch.add("Spring");
		
		// checkbox는 java에서도 동일
		Checkbox box = new Checkbox("Travel");
		
		frame.add(bt);
		frame.add(t1);
		frame.add(ch);
		frame.add(box);
	}
}
