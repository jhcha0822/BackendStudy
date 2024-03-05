package com.sds.project0305.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 사용자들이 GUI 프로그램에서 이벤트를 일으키면 1차적으로 OS가 감지하여
// 프로그램에 전달해 준다.
// 현재 JVM이 이벤트 정보를 전달받는데,
// JVM은 자바에서 사용되는 이벤트로 재해석하여 정보를 담고 있는 이벤트 객체의 인스턴스를 생성한다.
// 클릭이벤트 정보를 OS로 전달받으면 JVM은 ActionEvent 객체를 메모리에 생성하게 된다.
// 이때 ActionEvent 객체는 개발자에게 전달되어 개발자가 원하는 처리를 진행할 수 있는데
// 이렇게 개발자에게 이벤트 객체가 전달되려면 실시간으로 해당 이벤트가 발생할 때마다 이를 감지할 수 있는 객체가 필요하다.
// 이러한 객체를 가리켜 Listener라 한다.
// 이 리스너는 인터페이스로 되어 있으므로 자식 객체를 재정의하여 자식을 new해야 한다.
public class MyActionListener implements ActionListener{

	@Override
	// 사용자에 의해 액션이벤트가 발생할 때 마다 이 메서드가 호출되어진다.
	// JVM이 생성한 이벤트 정보 객체인 ActionEvent 인스턴스가 아래의 매개변수인 e 변수로
	// 대입이 되어진다. 따라서 개발자가 더 많은 이벤트 정보를 보고 싶다면 매개변수인 e를 사용하면 된다.
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ActionEvent가 발생할 때 개발자가 원하는 내용을 작성
		System.out.println(e);
	}
	
}
