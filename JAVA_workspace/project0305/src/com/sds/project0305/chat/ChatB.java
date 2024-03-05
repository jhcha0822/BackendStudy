package com.sds.project0305.chat;

import java.awt.BorderLayout;
// import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// import com.sds.project0305.event.MyKeyListener;

public class ChatB extends Frame implements KeyListener{

	TextArea area;
	Panel p_south;
	TextField t;
	ChatA chatA;
	
	public ChatB(ChatA chatA) {
		area = new TextArea();
		p_south = new Panel();
		t = new TextField(30);
		this.chatA = chatA;
		
		// Style
		area.setBackground(Color.YELLOW);
		
		// Append
		add(area); // BorderLayout.CENTER
		p_south.add(t);
		add(p_south, BorderLayout.SOUTH);
		
		// 입력 TextField와 Listener 연결
		// MyKeyListener myKeyListener = new MyKeyListener();
		// t.addKeyListener(myKeyListener);
		t.addKeyListener(this);
		
		// 가시성 및 크기 설정
		// setSize(300, 400);
		setBounds(550, 300, 300, 400);
		setVisible(true);
	}

	// KeyListener의 추상 메서드 오버라이드
		@Override
		public void keyTyped(KeyEvent e) { }
		@Override
		public void keyPressed(KeyEvent e) { }
		@Override
		public void keyReleased(KeyEvent e) {
			// ChatB 입장에서는 자신의 area와, ChatA의 area에 로그 출력
			if(e.getKeyCode()==KeyEvent.VK_ENTER) { // java에서는 상수에 직관성, 의미가 부여됨
				this.area.append(t.getText()+"\n");
				this.chatA.area.append(t.getText()+"\n");
				this.t.setText("");
			}
		}
}
