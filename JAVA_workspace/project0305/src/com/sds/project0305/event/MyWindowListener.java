package com.sds.project0305.event;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// 사용자가 frame 창을 대상으로 각종 이벤트를 일으키면 발생하는 이벤트가 WindowEvent
// WindowEvent를 실시간으로 청취하는 WindowListener
public class MyWindowListener implements WindowListener{

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		// 윈도우 생성 시
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		// 윈도우 닫기 누를 때
		System.exit(0); // 프로세스 종료
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		// 윈도우 닫혔을 때
		// 시점이 늦어 확인이 어렵다
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		// 윈도우가 최소화되어 아이콘이 되었을 때
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		// 윈도우가 최소화에서 복귀될 때
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		// Focus되었을 때
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		// Focus를 빠져나갈 때
	}

}
