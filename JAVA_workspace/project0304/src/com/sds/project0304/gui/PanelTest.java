/*
package com.sds.project0304.gui;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;

// java의 컴포넌트 중 Panel은 판때기
// 다른 컴포넌트와 다르게 배치능력을 보유함
// 즉, 배치관리자에 적용이 가능함
// 내부적으로 디자인 영역을 나누는 div와 유사함
// div와 동일하게 투명함

public class PanelTest {
	Frame frame = new Frame("Panel Study");
	FlowLayout flow = new FlowLayout(); 
	frame.setLayout(flow);
	
	// Panel 2개 생성
	Panel p1 = new Panel();
	Panel p2 = new Panel();
	
	// Panel
	// 1) 배치관리자 사용 가능
	//  - 만일 개발자가 배치관리자를 적용하지 않으면 default로 FlowLayout 적용
	// 2) Frame의 기능인 컴포넌트 add() 가능: 컨테이너(parent)가 될 수 있음
	
	p1.setBackground(new Color(255, 130, 135));
	p2.setBackground(new Color(255, 78, 97));
	
	p1.setPreferredSize(new Dimension(200, 150));
	p1.setPreferredSize(new Dimension(200, 150));
	
	frame.add(p1);
	frame.add(p2);
	
	frame.setVisible(true);
	frame.setSize(300, 350);
	
	
}
*/