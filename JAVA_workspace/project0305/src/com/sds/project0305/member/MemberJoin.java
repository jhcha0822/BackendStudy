package com.sds.project0305.member;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// java.awt 패키지는 OS에 따라 다른 디자인으로 보여질 수 있기에
// 이를 개선한 javax.swing 패키지를 이용, OS중립적인 분위기를 가진다.
// 기존 awt 컴포넌트 이름 앞에 J 접두어 붙이면 해결
// 여전히 java.awt의 layout(배치관리자)과 event는 유지
public class MemberJoin extends JFrame {
	JLabel la_title;      // 제목
	JPanel p_content; // 가운데
	JLabel la_id, la_pw, la_jumin, la_gender, la_hobby, la_file, la_zip;
	
	JTextField t_id;        // ID
	JPasswordField t_pw; // PW
	JTextField t_jumin1, t_jumin2; // 주민번호 앞, 뒤
	String[] hobbyName = {"여행", "운동", "코딩", "독서"}; 
	Checkbox[] hobby = new Checkbox[4]; //Checkbox의 그룹화 -> 라디오
	JTextField t_profile;  // 프로필사진 경로
	JTextField t_filename, t_ext;  // 파일명과 확장자
	JTextField t_zip1, t_zip2; // 우편번호 앞, 뒤
	JButton bt_regist;    // 가입버튼
	
	public MemberJoin() {
		// 생성
		la_title = new JLabel("회원 가입");
		p_content = new JPanel();
		la_id = new JLabel("ID");
		la_pw = new JLabel("PW");
		la_jumin = new JLabel("주민 번호");
		la_gender = new JLabel("성별");
		la_hobby = new JLabel("취미");
		la_file = new JLabel("프로필 사진");
		la_zip = new JLabel("우편번호");
		
		t_id = new JTextField();
		t_pw = new JPasswordField();
		t_jumin1 = new JTextField();
		t_jumin2 = new JTextField();
		
		for(int i=0; i<hobby.length; i++)
			hobby[i] = new Checkbox(hobbyName[i]);
		
		t_profile = new JTextField();
		t_filename = new JTextField();
		t_ext = new JTextField();
		t_zip1 = new JTextField();
		t_zip2 = new JTextField();
		bt_regist = new JButton("가입");
		
		// Style
		la_title.setFont(new Font("Verdana", Font.BOLD, 50));
		p_content.setBackground(Color.CYAN);
		
		// ID Style
		Dimension d = new Dimension(270, 25);
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		
		// Append
		add(la_title, BorderLayout.NORTH);
		p_content.add(la_id);
		p_content.add(t_id);
		add(p_content); // CENTER
		
		// Window 설정
		setSize(700, 500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MemberJoin();
	}
}
