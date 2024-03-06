package com.sds.project0306.img;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class MoveImg extends JFrame implements KeyListener{
	XCanvas can;
	
	public MoveImg() {
		can = new XCanvas();
		add(can); // BorderLayout.CENTER
		can.repaint();
		
		can.addKeyListener(this); // 리스너 연결
		
		setSize(1200, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // X누르면 프로세스 종료
	}
	
	public void keyPressed(KeyEvent e) {
		// KeyEvent 객체가 보유한 상수로 방향키 사용
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				can.x-=5; 
				break;
			case KeyEvent.VK_UP:
				can.y-=5; 
				break;
			case KeyEvent.VK_RIGHT:
				can.x+=5; 
				break;
			case KeyEvent.VK_DOWN:
				can.y+=5; 
				break;
		}
		can.repaint();
	}
	
	public static void main(String[] args) {
		new MoveImg();
	}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}
