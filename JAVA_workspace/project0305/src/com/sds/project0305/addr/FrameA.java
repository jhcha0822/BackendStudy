package com.sds.project0305.addr;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

public class FrameA extends Frame{
	Button bt_open; // 다른 창을 띄울 버튼
	Button[] bt_color = new Button[7];
	Color[] colorArray = {Color.RED, Color.ORANGE, Color.YELLOW, Color.BLUE, Color.MAGENTA, Color.GRAY};
	
	public FrameA() {
		bt_open = new Button("open");
		this.setLayout(new FlowLayout());
		add(bt_open);
		for(int i=0; i<colorArray.length; i++) {
			bt_color[i] = new Button();
			bt_color[i].setBackground(colorArray[i]);
			bt_color[i].setPreferredSize(new Dimension(50, 35));
			add(bt_color[i]);
		}
		
		BtnListener btnListener = new BtnListener(this);
		
		// 열기 버튼과 리스너 연결
		bt_open.addActionListener(btnListener);
		
		// 7개의 Button과 Listener 연결
		for(int i=0; i<colorArray.length; i++)
			bt_color[i].addActionListener(btnListener);
		
		this.setSize(300, 400);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new FrameA();
	}
}
