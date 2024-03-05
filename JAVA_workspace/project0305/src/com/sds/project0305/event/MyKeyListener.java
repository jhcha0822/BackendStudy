package com.sds.project0305.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// 사용자가 키보드와 관련된 이벤트를 발생시키면 아래의 리스너 객체가 감지
// 감지했을 때 어떤 코드를 작성할 지 여부는 개발자의 몫이므로 추상메서드만 가진 인터페이스로 제공
public class MyKeyListener implements KeyListener{

	// 타이핑할때
	public void keyTyped(KeyEvent e) {
	}

	// 누를 때 (총 발사 등)
	public void keyPressed(KeyEvent e) {
	}

	// 눌렀다 뗄 때 (입력완료시)
	public void keyReleased(KeyEvent e) {
		// System.out.println(e);
		if(e.getKeyCode() == 10) { // Enter 입력시
			System.out.println("Enter");
		}
	}

}
