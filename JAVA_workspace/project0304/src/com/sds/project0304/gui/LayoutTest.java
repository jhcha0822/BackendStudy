package com.sds.project0304.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

// JAVA의 UI 컴포넌트들을 화면 어디에 올려놓을지 -> 배치
// java.awt 배치는 LayoutManager라 불리는 객체들로 지원됨

// 배치의 유형
// 1) BorderLayout: 동서남북 방위의 배치
// 2) GridLayout: 행과 열을 갖는 격자 방식의 배치
// 3) FlowLayout: 수평 및 수직의 방향성으로 요소들을 흘러가게 만드는 방식의 배치
// 4) CardLayout: 여러장의 장면 중 누구를 보여줄지를 결정하는 배치방식, 오직 하나씩만 디스플레이
// 5) GridBag: GridLayout을 좀 더 세분화하여 배치하는 방식

public class LayoutTest {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;
	public static void main(String[] args) {	
		Frame frame = new Frame(); // 윈도우 생성
		frame.setVisible(true);         // 가시성 보이게 설정
		frame.setSize(WIDTH, HEIGHT);      // w 600, h 500, 같은 클래스이기에 LayoutTest 생략 가능
		
		// 버튼 5개를 준비하여 각각 BorderLayout 객체의 원하는 방위에 부착
		BorderLayout borderLayout = new BorderLayout();
		
		// 지금부터는 윈도우 창이 BorderLayout에 적용을 받음
		frame.setLayout(borderLayout);
		Button[] btn = new Button[5]; // 객체자료형도 자료형이기에 배열로 선언 가능
		btn[0] = new Button("North");
		btn[1] = new Button("East");
		btn[2] = new Button("South");
		btn[3] = new Button("West");
		btn[4] = new Button("Center");
		
		// 각 영역에 컴포넌트 부착
		// 상수: public static final, 변수와 구분을 위해 모두 대문자로 작성하는 것이 관례
		frame.add(btn[0], BorderLayout.NORTH);
		frame.add(btn[1], BorderLayout.EAST);
		frame.add(btn[2], BorderLayout.SOUTH);
		frame.add(btn[3], BorderLayout.WEST);
		frame.add(btn[4], BorderLayout.CENTER);
		
		// 만일 Frame에 개발자가 아무런 배치 관리자를 적용하지 않으면
		// default로 BorderLayout이 적용됨
	}
}
