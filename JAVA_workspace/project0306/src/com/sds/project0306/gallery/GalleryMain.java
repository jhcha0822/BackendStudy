package com.sds.project0306.gallery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GalleryMain extends JFrame implements ActionListener{
	GalleryCanvas can;
	JPanel p_south;
	JButton bt_prev, bt_next;
	
	public GalleryMain() {
		can = new GalleryCanvas(this);//빈 도화지 생성 
		
		p_south = new JPanel();
		bt_prev = new JButton("◀");
		bt_next = new JButton("▶");
		
		can.setPreferredSize(new Dimension(400, 300));
		
		// ADD
		add(can);
		p_south.add(bt_prev);
		p_south.add(bt_next);
		add(p_south, BorderLayout.SOUTH);//남쪽에 패널 부착 
		
		can.setBackground(Color.YELLOW); // 확인
		
		//닫기 버튼 누르면, 프로세스 종료하는 메서드 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //System.exit(0) 동일
				
		//버튼과 리스너 연결 
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
				
		setSize(400, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new GalleryMain();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 자바에서는 이벤트를 일으킨 컴포넌트를 가리켜 이벤트 소스라 함
		// e 변수로부터 누가 눌렸는지를 확인 가능함
		// Object obj =  e.getSource();
		JButton obj =  (JButton)e.getSource(); // 형변환
		
		if(obj == bt_prev) {
			if(can.index > 0)
				can.index--;
			else
				JOptionPane.showMessageDialog(this, "처음 이미지입니다.");
			can.repaint();
		}
		else if(obj == bt_next) {
			if(can.index < 8)
				can.index++;
			else
				JOptionPane.showMessageDialog(this, "마지막 이미지입니다.");
			can.repaint();
		}
	}

}
