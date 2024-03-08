package com.sds.project0308.mouse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

// 게임의 모든 요소들이 등장하게 될 패널
public class GamePanel extends JPanel{
	public static final int WIDTH = 1500;
	public static final int HEIGHT = 850;
	Hero hero;
	Thread thread; // 재정의하지 않고 코드 내에 작성
	
	// 윈도우의 크기를 패널이 결정하도록 한다.
	public GamePanel() {
		// 내용물인 Panel에 크기 설정
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		hero = new Hero("D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image1.png", 100, 200, 200, 213, 5, 5); //String path, int x, int y, int width, int height, int velX, int velY
				
		// 블럭 내의 내용은 이름이 없는 클래스로 사용 가능하다
		// 즉, 클래스 내의 클래스로, '내부 익명 클래스'라 한다
		// 클래스의 재사용성이 떨어질 때, .java를 사용해 주소를 전달하는 것을 방지하기 위해 이용
		// 1) 재사용 가능성이 별로 없는 클래스 경우 내부익명으로 이벤트 구현시 등에 자주 사용
		// 2) 바깥쪽 외부 클래스의 멤버를 함께 쓰고 싶을 때
		thread = new Thread() { // Thread를 재정의
			public void run() {    // 주로 오버라이드 목적  
				while(true) {
					try {
						Thread.sleep(10);
						gameLoop();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		thread.start(); // 스레드 가동
	}
	
	public void gameLoop() {
		System.out.println("gameLoop calling");
		repaint();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// g.drawImage(hero.image, hero.x, hero.y, hero.width, hero.height, this);
		hero.tick();
		hero.render(g);
	}
}
