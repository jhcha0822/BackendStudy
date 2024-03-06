package com.sds.project0306.graphic;

import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JFrame;

//                현실 그래픽 구성 요소 | 전산 그래픽 구성 요소
// 그림의 주체 |         화가           |      컴포넌트 스스로
// 그림 그리기 |         붓 등          |      paint() 메서드
// 그림의 대상 |        도화지          |     컴포넌트 자신에게
// 다양한 색상 |        팔레트          | 그래픽 전담 객체(java Graphics)

// JFrame 대상 증명
public class GraphicTest extends JFrame{
	MyButton bt;
	Canvas can; // 컴포넌트 중 도화지 역할을 하는 컴포넌트
					 // 개발자가 적극적으로 paint() 메서드를 재정의해야 함
	
	// 컴포넌트 스스로가 자기 자신을 잘 그리고 있었는데
	// 이 함수를 오버라이드하여 새로 규정
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("프레임 그림 방해");
	}
	
	public GraphicTest() {
		bt = new MyButton();
		setLayout(new FlowLayout());

		add(bt);
		
		setSize(600, 500);
		setVisible(true);
	}
	
	// 자바의 모든 컴포넌트는 paint() 메서드가 있으나
	// Canvas등과 같이 재정의하는 것이 요구되는 경우나
	// 하지 말아야 할 경우가 있다
	
	public static void main(String[] args) {
		new GraphicTest();
	}
}
