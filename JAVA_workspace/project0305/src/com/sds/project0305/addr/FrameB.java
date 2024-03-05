package com.sds.project0305.addr;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;

public class FrameB extends Frame{

	public FrameB() {
		setBackground(Color.YELLOW);
		setSize(300, 400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		FrameB b = new FrameB();
	}
	
}
