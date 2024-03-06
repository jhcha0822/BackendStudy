package com.sds.project0306.painting;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

// 그림판
public class Painter extends JFrame implements MouseMotionListener{
	PaintCanvas can;
	
	// 자바에서는 배열이 생성 시 그 크기를 미리 결정해야 하므로, 크기가 동적으로 변하는 프로그램에서
	// 사용하기에 적당하지 않다. 동적으로 변하는 데이터 구조, collection framework을 사용한다.
	// collection framework: 자바의 자료구조 패키지
	// - 객체를 모아서 처리할 때 유용한 기능들을 모아놓은 객체의 패키지
	// 1) 순서 있게: list
	// 2) 순서 없이: set
	// 3) key-value: map
	// 주의) 오직 객체 자료형만을 대상으로 함
	
	// 본 예제에서는 List의 자식 중 ArrayList 사용
	// 차이점
	// 1) 배열의 크기가 동적으로 늘어남
	// 2) 배열은 기본 자료형을 담을 수 있으나, ArrayList는 오직 객체자료형만
	// 3) 
	
	ArrayList list;
	
	public Painter() {
		list = new ArrayList();
		can = new PaintCanvas(this);
		
		add(can);
		
		// 캔버스와 리스너 연결
		can.addMouseMotionListener(this);
		
		setSize(1400, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Painter();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// 마우스 x, y좌표 구하기
		can.x = e.getX();
		can.y = e.getY();
		
		// x, y를 계속 누적해놓고 canvas의 paint 메서드에서는 누적된 모든 점을 그리게 해야 함
		int[] pos = {can.x, can.y};
		list.add(pos); // 자바에서는 배열이 객체이기에 가능함
		
		System.out.println("현재까지 누적된 점의 수는 "+list.size());
		can.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
