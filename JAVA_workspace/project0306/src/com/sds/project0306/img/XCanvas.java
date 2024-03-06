package com.sds.project0306.img;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

// canvas를 재정의하기 위한 클래스
public class XCanvas extends Canvas{
	// 자바에서 이미지를 얻는 방법은 여러가지가 있으나 먼저 ToolKit을 이용해 이미지를 얻어보자
	Toolkit kit = Toolkit.getDefaultToolkit(); // 추상 클래스이기에 자체적으로 인스턴스를 얻는 메서드가 지원됨 (Static)
	Image image;
	
	int x=100, y=300, width=50, height=50;
	
	public XCanvas() {
		image = kit.getImage("D:\\MULTICAMPUS\\JAVA_workspace\\project0306\\images\\mario.png");
	}
	
	public void paint(Graphics g) {
		// g.drawImage(이미지 객체, x축, y축, WIDTH, HEIGHT, 이미지의 관찰자);
		g.drawImage(image, x, y, width, height, this);
	}
}
