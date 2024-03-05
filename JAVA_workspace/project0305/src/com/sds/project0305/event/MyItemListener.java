package com.sds.project0305.event;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// Choice 컴포넌트에서 현재 아이템이 아닌 다른 아이템을 선택할 때 감지하는 이벤트
public class MyItemListener implements ItemListener{

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e);
	}
	
}
