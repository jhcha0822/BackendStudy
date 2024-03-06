package com.sds.project0306.painting;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

// 그림이 그려질 캔버스 정의
public class PaintCanvas extends Canvas{
	int x, y;
	Painter painter;
	
	public PaintCanvas(Painter painter) {
		this.painter = painter;
		this.setBackground(Color.YELLOW);
	}
	
	@Override
	public void paint(Graphics g) {
		for(int i=0; i<painter.list.size(); i++) {
			// collection framework에 입력과 반환은 object형
			Object obj = painter.list.get(i);
			int[] pos=(int[])obj;
			
			g.fillOval(pos[0], pos[1], 4, 4);
		}
	}
}
