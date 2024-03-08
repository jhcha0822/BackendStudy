package com.sds.project0308.bar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressTest extends JFrame{

	JButton bt;
	JProgressBar bar;

	int n;
	boolean flag = true;
	
	Thread thread; // Bar를 자동으로 증가시킬 스레드
	
	public ProgressTest() {
		bt = new JButton("Download");
		bar = new JProgressBar();
		
		setLayout(new FlowLayout());
		
		// 스타일
		bar.setPreferredSize(new Dimension(760, 50));
		bar.setBackground(Color.CYAN);
		bar.setForeground(Color.BLUE);
		
		add(bt);
		add(bar);
		
		// 버튼과 리스너 연결
		bt.addActionListener(new ActionListener() {
			// 내부 익명 클래스
			public void actionPerformed(ActionEvent e) {
				// 내부 익명 클래스
				thread = new Thread() {
					public void run() {
						while(flag) {
							download();
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};
				thread.start();
			}
		});
		
		setSize(800, 250);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void download() {
		n += 5;
		bar.setValue(n);
		bar.setString(n+"%");
		bar.setStringPainted(true);
		if(n >= 100) {
			n = 100;
			flag = false;
		}
	}
	
	public static void main(String[] args) {
		new ProgressTest();
	}

}
