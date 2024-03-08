package com.sds.project0308.mouse;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseFollow extends JFrame {
	JLabel la_info;
	GamePanel gamePanel;
	
	public MouseFollow() {
		gamePanel = new GamePanel();
		add(gamePanel);
		
		// 윈도우의 크기를 패널이 결정하도록 한다.
		pack();
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MouseFollow();
	}

}
