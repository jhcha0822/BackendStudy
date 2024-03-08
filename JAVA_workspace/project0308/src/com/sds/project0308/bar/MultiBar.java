package com.sds.project0308.bar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

// bar를 3개 올려두고 서로 다른 속도로 움직이도록 처리
// 스레드로 서로 다른 속도 구현
// 하나의 프로세스 내에서 서로 다른 독립적인 스레드 구현
public class MultiBar extends JFrame{
	JButton bt;
	JProgressBar[] bars = new JProgressBar[3]; // 배열 공간 확보
	
	public MultiBar() {
		bt = new JButton("Download");
		setLayout(new FlowLayout());
		add(bt);
		
		// bar 3개 생성
		for(int i=0; i<bars.length; i++) {
			bars[i] = new  JProgressBar();
			bars[i].setPreferredSize(new Dimension(800, 60));
			bars[i].setBackground(Color.CYAN);
			bars[i].setForeground(Color.BLUE);
			bars[i].setStringPainted(true);
			
			add(bars[i]);
		}
		
		// 버튼과 리스너 연결
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startDownload();
			}
		});
		
		// Window
		setSize(800, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void startDownload() {
		BarThread t1 = new BarThread(bars[0], 2);
		BarThread t2 = new BarThread(bars[1], 3);
		BarThread t3 = new BarThread(bars[2], 4);
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	public static void main(String[] args) {
		new MultiBar();
	}

}
