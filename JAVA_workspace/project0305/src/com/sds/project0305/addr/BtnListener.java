package com.sds.project0305.addr;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 사용자의 액션이벤트를 감지할 리스너 클래스 정의
public class BtnListener implements ActionListener{
	
	FrameA frameA; // 객체 인스턴스 소멸 전까지 살아있음
	FrameB frameB; // null
	
	public BtnListener(FrameA frameA) {
		this.frameA = frameA; // 주소를 받아와서 멤버변수로 업데이트
	}
	
	// 사용자가 액션이벤트를 일으킬때마다 호출되는 메서드
	public void actionPerformed(ActionEvent e) {
		// 이벤트 정보가 들어 있는 e 객체에서 어떤 버튼이 눌렸는지 알아내자
		// 이벤트 프로그래밍에서 이벤트를 일으킨 주체: 이벤트 소스
		// 이벤트 소스를 얻어오기
		Object obj = e.getSource(); // 이벤트를 일으킨 객체 Object 반환
		// 여러 컴포넌트에 action을 줄 수 있기에 최상위객체 object로 제공됨
		// Button btn = (Button)obj; // 액션이벤트를 버튼에 연결했기에 형변환
		
		
		//FrameB b = new FrameB();
		// open 버튼을 누를 때
		if(obj == frameA.bt_open)
			if(frameB == null) // window가 존재하지 않을 때만 new 허용
				frameB = new FrameB();
		// 색상 버튼을 누를 때
		else if(obj == frameA.bt_color[0])
			frameB.setBackground(frameA.colorArray[0]);
		else if(obj == frameA.bt_color[1])
			frameB.setBackground(frameA.colorArray[1]);
		else if(obj == frameA.bt_color[2])
			frameB.setBackground(frameA.colorArray[2]);
		else if(obj == frameA.bt_color[3])
			frameB.setBackground(frameA.colorArray[3]);
		else if(obj == frameA.bt_color[4])
			frameB.setBackground(frameA.colorArray[4]);
		else if(obj == frameA.bt_color[5])
			frameB.setBackground(frameA.colorArray[5]);
		else if(obj == frameA.bt_color[6])
			frameB.setBackground(frameA.colorArray[6]);			
	}

}
