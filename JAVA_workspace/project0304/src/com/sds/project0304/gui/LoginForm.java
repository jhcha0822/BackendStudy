package com.sds.project0304.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class LoginForm {
	public static void main(String[] args) {
		Frame frame = new Frame();
		
		Panel content = new Panel();
		Panel footer = new Panel();
		
		content.setBackground(Color.YELLOW);
		footer.setBackground(Color.GREEN);
		
		// content 패널에 라벨 2개, 텍스트필드 2개 삽입
		Label la_id = new Label("ID");
		Label la_pw = new Label("PW");
		TextField t_id = new TextField();
		TextField t_pw = new TextField();

		// 컴포넌트 별 스타일 적용
		la_id.setPreferredSize(new Dimension(120, 30));
		t_id.setPreferredSize(new Dimension(120, 30));
		la_pw.setPreferredSize(new Dimension(120, 30));
		t_pw.setPreferredSize(new Dimension(120, 30));
		
		// footer에 부착할 버튼 2개 생성
		Button bt_login = new Button("Login");
		Button bt_join = new Button("Join");
		
		// 생성된 컴포넌트 4개를 content에 자식으로 붙이기
		content.add(la_id);
		content.add(t_id);
		content.add(la_pw);
		content.add(t_pw);
		
		footer.add(bt_login);
		footer.add(bt_join);
		
		frame.add(content, BorderLayout.CENTER);
		frame.add(footer, BorderLayout.SOUTH);
		
		frame.setSize(300, 155);
		frame.setVisible(true);
	}
}
