package com.sds.project0305.event;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;

// 현재 정의하고 있는 클래스를 프레임 자체로 정의
public class EventTest extends Frame{
	// has-a 관계: 멤버변수가 객체자료형일 때
	// 멤버변수가 main 내부에 있으면 지역변수
	Button bt;
	int x; // 객체가 아니므로 has-a가 아니다
	
	TextField t;
	Choice ch; // select 박스
	
	public EventTest() {
		bt = new Button("Click");
		t = new TextField(15);
		ch = new Choice();
		// choice item을 넣어보기: option
		ch.add("IRENE");
		ch.add("SEULGI");
		ch.add("WENDY");
		ch.add("JOY");
		ch.add("YERI");
		
		// this. 생략가능
		setLayout(new FlowLayout());
		add(bt);
		add(t);
		add(ch);
		
		// Button에 Listener 연결 (인스턴스)
		MyActionListener my = new MyActionListener();
		bt.addActionListener(my);		
		
		// 텍스트필드에 KeyboardEvent Listener 연결
		t.addKeyListener(new MyKeyListener());
		
		// Choice에 아이템 변경 리스너 연결
		ch.addItemListener(new MyItemListener());
		
		// 현재 창에 윈도우 리스너 연결
		this.addWindowListener(new MyWindowListener());
		
		setBackground(Color.YELLOW);
		setSize(300, 400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// 객체 생성 후 할 일이 없다면 굳이 변수로 사용할 이유가 없음
		new EventTest();
	}

}
