package com.sds.project0305.chat;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// import com.sds.project0305.event.MyKeyListener;

public class ChatA extends Frame implements ActionListener, KeyListener{

	TextArea area;
	Panel p_south;
	TextField t;
	Button bt;
	ChatB chatB;
	
	public ChatA() {
		area = new TextArea();
		p_south = new Panel();
		t = new TextField(28);
		bt = new Button("open");
		
		// Style
		area.setBackground(Color.YELLOW);
		
		// Append
		add(area); // BorderLayout.CENTER
		p_south.add(t);
		p_south.add(bt);
		add(p_south, BorderLayout.SOUTH);
		
		// Button과 Listener 연결
		// OpenListener openListener = new OpenListener();
		// bt.addActionListener(openListener);
		bt.addActionListener(this);
		
		// 입력 TextField와 Listener 연결
		// MyKeyListener myKeyListener = new MyKeyListener();
		// t.addKeyListener(myKeyListener);
		t.addKeyListener(this);
		
		// 가시성 및 크기 설정
		// setSize(300, 400);
		setBounds(200, 300, 300, 400); // x y w h
		setVisible(true);
	}
	
	// ActionListener의 추상 메서드 오버라이드
	@Override
	public void actionPerformed(ActionEvent e) {
		if(chatB == null)
			chatB = new ChatB(this);
	}
	
	// KeyListener의 추상 메서드 오버라이드
	@Override
	public void keyTyped(KeyEvent e) { }
	@Override
	public void keyPressed(KeyEvent e) { }
	@Override
	public void keyReleased(KeyEvent e) {
		// ChatA 입장에서는 자신의 area와, ChatB의 area에 로그 출력
		if(e.getKeyCode()==KeyEvent.VK_ENTER) { // java에서는 상수에 직관성, 의미가 부여됨
			this.area.append(t.getText()+"\n");
			this.chatB.area.append(t.getText()+"\n");
			this.t.setText("");
		}
	}
	
	public static void main(String[] args) {
		new ChatA();
	}

}
