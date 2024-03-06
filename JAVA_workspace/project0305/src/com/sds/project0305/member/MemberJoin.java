package com.sds.project0305.member;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// java.awt 패키지는 OS에 따라 다른 디자인으로 보여질 수 있기에
// 이를 개선한 javax.swing 패키지를 이용, OS중립적인 분위기를 가진다.
// 기존 awt 컴포넌트 이름 앞에 J 접두어 붙이면 해결
// 여전히 java.awt의 layout(배치관리자)과 event는 유지

// 이벤트 프로그래밍의 3단계 절차 
// 1) 적절한 리스너 선택 
// 2) 해당 리스너의 메서드 오버라이드(개발자가 원하는 코드로 재정의)
// 3) 컴포넌트와 리스너 연결

public class MemberJoin extends JFrame implements WindowListener, ActionListener, KeyListener{
	JLabel la_title;      // 제목
	JPanel p_content; // 가운데
	JLabel la_id, la_pw, la_jumin, la_gender, la_hobby, la_file, la_zip;
	
	JTextField t_id;        // ID
	JPasswordField t_pw; // PW
	JTextField t_jumin1, t_jumin2; // 주민번호 앞, 뒤
	
	//Checkbox를 그룹화 시켜놓아야 라디오가 등장, html 에서도  radio는 배열로 존재시켜야 함 
	CheckboxGroup cg;
	Checkbox man;
	Checkbox woman;
	
	Checkbox[] hobby = new Checkbox[4]; //Checkbox의 그룹화 -> 라디오
	String[] hobbyName = {"Travel", "Workout", "Programming", "Game"}; 
	
	JTextField t_profile;  // 프로필사진 경로
	JTextField t_filename, t_ext;  // 파일명과 확장자
	JTextField t_zip1, t_zip2; // 우편번호 앞, 뒤
	JButton bt_regist;    // 가입버튼
	
	int hobby_count = 0; // 체크박스의 체크 횟수를 받아오는 변수
	
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
		cg = new CheckboxGroup();
		man = new Checkbox("man", cg, false);
		woman = new Checkbox("woman", cg, false);
		
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
		Dimension d = new Dimension(290, 28);
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		
		// PW Style
		la_pw.setPreferredSize(d);
		t_pw.setPreferredSize(d);
		
		// 주민번호 Style
		la_jumin.setPreferredSize(d);
		Dimension d2 = new Dimension(144, 28);
		t_jumin1.setPreferredSize(d2);
		t_jumin2.setPreferredSize(d2);
		
		// 성별
		la_gender.setPreferredSize(d);
		
		// 취미 라벨 Style
		la_hobby.setPreferredSize(d);
		
		// 프로필 Style
		la_file.setPreferredSize(d);
		Dimension d3 = new Dimension(110, 28);
		t_profile.setPreferredSize(d3);
		t_filename.setPreferredSize(d3);
		t_ext.setPreferredSize(d3);
		
		// 우편번호 Style
		la_zip.setPreferredSize(d);
		t_zip1.setPreferredSize(d2);
		t_zip2.setPreferredSize(d2);
		
		// Append
		add(la_title, BorderLayout.NORTH);
		add(p_content); // CENTER
		
		p_content.add(la_id);
		p_content.add(t_id);
		
		p_content.add(la_pw);
		p_content.add(t_pw);
		
		p_content.add(la_jumin);
		p_content.add(t_jumin1);
		p_content.add(t_jumin2);
		
		p_content.add(la_gender);
		p_content.add(man);
		p_content.add(woman);
		
		p_content.add(la_hobby);
		for(int i=0; i<hobby.length; i++) //취미를 반복문으로 부착
			p_content.add(hobby[i]);
		
		p_content.add(la_file);
		p_content.add(t_profile);
		p_content.add(t_filename);
		p_content.add(t_ext);
		
		p_content.add(la_zip);
		p_content.add(t_zip1);
		p_content.add(t_zip2);
		
		p_content.add(bt_regist);
		
		// 현재 프레임과 리스너 연결
		// this: 자기 자신을 가리키는 레퍼런스 변수
		this.addWindowListener(this);
		
		//버튼과 리스너 연결
		bt_regist.addActionListener(this);
		
		//주민번호 뒷자리 텍스트필드와 리스너 연결
		t_jumin2.addKeyListener(this);
		
		// Window 설정
		setSize(700, 500);
		setVisible(true);
	}
	
	public void checkForm() {
		// getText() 메서드의 반환값은 String이고,
		// String은 객체이므로 api활용 가능
		if(t_id.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
			t_id.requestFocus();
			return;
		}
		// 비밀번호 유효성 체크
		char[] pw = t_pw.getPassword();
		if(pw.length < 1) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
			t_pw.requestFocus();
			return;
		}
		
		// 웹: 서버로 전송
		// 독립실행형: 오라클에 직접 insert
		System.out.println("오라클에 등록할 예정");
	}
	
	public static void main(String[] args) {
		MemberJoin mj = new MemberJoin();
	}

	public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	public void actionPerformed(ActionEvent e) {
		// 체크 박스
				
		// 확인 버튼
		checkForm();
	}

	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		// t_jumin2에 대해 키보드 눌렀다 뗄 때 호출
		char n = t_jumin2.getText().charAt(0); // 주민번호 뒷자리 String 중 첫 문자
		// '1' --> 정수 1
		// 자바에서는 모든 기본 자료형을 객체자료형으로 바꾼다거나, 역으로 객체 자료형을 기본자료형으로
		// 변환하는 기능을 지원하는 객체가 있으며, 이 객체를 가리켜 wrapper 클래스라 한다
		// byte - Byte, short - Short, int - Integer ...
		String s = Character.toString(n); // '1'을 "1"로 변환
		int result = Integer.parseInt(s);  // "1"을 1로 변환 
		if(result == 1) {
			man.setState(true);
		}
		else if(result == 2) {
			woman.setState(true);
		}
	}
}
