//package com.sds.project0308.mouse;
//
//import java.awt.FlowLayout;
//import java.awt.event.ActionEvent;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//
//public class TestWin extends JFrame{
//	
//	JButton bt;
//	
//	public TestWin() {
//		bt = new JButton("click");
//		setLayout(new FlowLayout());
//		add(bt);
//		
//		// 버튼과 리스너 연결
//		// 클릭이라는 행위를 다른 클래스파일로 만들어가면서까지 할 이유가 있는가?
//		// 이벤트 구현 시 내부 익명 클래스를 자주 사용한다
//		bt.addActionListener(new MyActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("click");
//			}
//		});
//		
//		setSize(300, 300);
//		setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//	}
//	
//	public static void main(String[] args) {
//		new TestWin();
//	}
//	
//}
