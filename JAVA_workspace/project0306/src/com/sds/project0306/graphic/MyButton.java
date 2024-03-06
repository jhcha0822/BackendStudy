package com.sds.project0306.graphic;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

// 평상시엔 버튼을 그냥 사용했지만
// 이번에는 버튼이 보유한 paint() 메서드를 뺏기 위해 별도의 클래스로 버튼을 상속받아
// paint()를 오버라이드 해보는 실험
public class MyButton extends JButton{
	@Override
	public void paint(Graphics g) { // g: 팔레트
		// TODO Auto-generated method stub
		System.out.println("버튼 그림 방해");
		g.setColor(Color.YELLOW);
		g.drawString("BuTtOn", 0, 10);
	}
	
	// 자바에는 Canvas와 같이 적극적으로 그림을 그려야 하는 컴포넌트가 있고
	// Sun사의 메서드를 오버라이드 하지 않는 것이 나은 경우가 있다
}
