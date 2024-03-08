package com.sds.project0308.mouse;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Hero {
	Toolkit kit = Toolkit.getDefaultToolkit(); // static메서드로 인스턴스 얻음
	Image image;
	int x;
	int y;
	int width;
	int height;
	int velX;
	int velY;
	float a = 0.1f; // 비율계수
	int targetX; // 목표지점
	
	public Hero(String path, int x, int y, int width, int height, int velX, int velY) {
		image = kit.getImage(path);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
	}

	public void tick() { // 물리량
		this.x += this.velX;
		this.y += this.velY;
	}
	
	// 감속도 공식 적용
	public void softTick() {
		// this.x = this.x + a*(targetX - this.x); // float 형변환이 필요함
	}
	
	public void render(Graphics g) { // 화면에 표시
		// GamePanel에서 모든 그래픽 처리가 이루어지기 때문에 그 주소를 얻어와야 함
		g.drawImage(image, x, y, width, height, null);
	}
}
