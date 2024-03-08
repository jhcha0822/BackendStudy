package com.sds.project0308.sprite;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// 여러 스프라이트 이미지를 대상으로 쓰레드를 할용한 애니메이션 효과를 내보자
public class Animation extends JFrame implements ActionListener{
	// JPanel p_content; 나만의 객체 정의하여 paint
	MyPanel p_content;
	JButton bt;
	
	public Animation() {
		bt = new JButton("start");
		p_content = new MyPanel();
		
		add(bt, BorderLayout.NORTH);
		add(p_content); // 프레임에 패널 부착
		
		// 리스너 연결
		bt.addActionListener(this);
		
		// Window
		setSize(500, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 현재 보고 있는 이미지의 다음 이미지를 보여주기
	public void move() {
		// MyPanel이 보유한 index 변수의 값을 증가시켜 그리게 하자
		p_content.index++;
		if(p_content.index >= p_content.imgArray.length)
			p_content.index = 0;
		p_content.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MyThread mt = new MyThread(this);
		mt.start();
	}
	
	public static void main(String[] args) {
		new Animation();
	}
}
