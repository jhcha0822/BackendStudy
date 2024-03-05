package com.sds.project0305.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;

public class WebSite extends Frame { // WebSite class is a frame
	// Frame has a buttons & panels
	Panel p_north; // Button들을 FLowLayout으로 부착할 패널
	Button[] bt = new Button[4];
	Label subNavi;
	Panel content;
	Label copyright;
	
	String[] webTitle = {"Intro", "About", "Product", "Contact"};
	
	public WebSite() { // Generate [has-a] component & load memory with constructor
		// JDK5부터 improved loop 제공하나 이 경우에는 고전적 for문이 유리
		p_north = new Panel();
		for(int i=0; i<bt.length; i++) {
			bt[i] = new Button(webTitle[i]);
			p_north.add(bt[i]);
		}
		subNavi = new Label("Irene Seulgi Wendy Joy Yeri");
		content = new Panel();
		copyright = new Label("copyright all reserved");
		
		// Style
		p_north.setBackground(Color.PINK);
		subNavi.setBackground(Color.YELLOW);
		subNavi.setPreferredSize(new Dimension(80, 300));
		content.setBackground(Color.GREEN);
		copyright.setBackground(Color.CYAN);
		copyright.setPreferredSize(new Dimension(500, 100));
		
		// Append at Frame. 
		// 생성자도 멤버 메서드임. 멤버 메서드 영역에서 인스턴스가 자기 자신은 this 레퍼런스 변수 이용
		// 이때 this.는 생략 가능
		// layout을 명시하지 않으면 BorderLayout이 default
		this.add(p_north, BorderLayout.NORTH);
		this.add(content); // default: CENTER
		this.add(copyright, BorderLayout.SOUTH);
		this.add(subNavi, BorderLayout.WEST);
		
		// Frame의 가시성 및 크기 설정
		this.setVisible(true);
		this.setSize(500, 400);
	}
	
	public static void main(String[] args) {
		WebSite w = new WebSite();
	}

}
