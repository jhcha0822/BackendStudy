package com.sds.project0304.gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

//JAVA의 UI 컴포넌트들을 화면 어디에 올려놓을지 -> 배치
//java.awt 배치는 LayoutManager라 불리는 객체들로 지원됨

//배치의 유형
//1) BorderLayout: 동서남북 방위의 배치
//2) GridLayout: 행과 열을 갖는 격자 방식의 배치
//3) FlowLayout: 수평 및 수직의 방향성으로 요소들을 흘러가게 만드는 방식의 배치
//4) CardLayout: 여러장의 장면 중 누구를 보여줄지를 결정하는 배치방식, 오직 하나씩만 디스플레이
//5) GridBag: GridLayout을 좀 더 세분화하여 배치하는 방식

public class FlowLayoutTest {
	public static void main(String[] args) {
		Frame frame = new Frame(); // 프레임 생성
		FlowLayout flow = new FlowLayout();
		frame.setLayout(flow);
		
		Button[] btn = new Button[20];
		for(int i=0; i<btn.length; i++) {
			btn[i] = new Button("button "+(i+1));
			frame.add(btn[i]);
		}
		
		frame.setSize(300, 400);
		frame.setVisible(true);
	}
}
