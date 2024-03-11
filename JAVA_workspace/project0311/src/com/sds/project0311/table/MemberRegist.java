package com.sds.project0311.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MemberRegist extends JFrame{
	//서쪽 영역 필요 컴포넌트들 
	JPanel p_west;
	JTextField t_id; //아이디
	JTextField t_phone; //연락처 
	JTextField t_gender; //성별 
	JButton bt;//등록 버튼 
		
	MyTableModel model = new MyTableModel(); // JTable이 보여줄 데이터에 대한 정보 제공
	//FruitModel model = new FruitModel(); // JTable이 보여줄 데이터에 대한 정보 제공
	JTable table;
	JScrollPane scroll;
	
	public MemberRegist() {
		p_west = new JPanel();
		t_id = new JTextField();
		t_phone = new JTextField();
		t_gender = new JTextField();
		
		// JTable의 3번째 생성자 방법: 생성자에 2차원배열 대입은
		// Table이 보여줄 데이터를 고정하기 때문에 데이터 변경이 불가함
		// 유지보수성이 떨어진다.
		// 4번째 생성자를 이용한다
		// TableModel을 이용하면 디자인(View) JTable과
		// 안에 보여질 데이터(Model)을 분리시켜 개발할 수 있기에 추후 다른 종류의 데이터를 보여주어야 한다 하여도
		// 크게 코드가 바뀌기 않는다. 기존의 JTable 코드를 유지 가능
		// 순수 데이터인 이차원 데이터 배열만 바꾸면 된다
		table = new JTable(model);
		scroll = new JScrollPane(table);
		bt = new JButton("등록");
		
		// Style 
		p_west.setPreferredSize(new Dimension(150,400));
		p_west.setBackground(Color.YELLOW);
		
		Dimension d = new Dimension(135, 45);
		t_id.setPreferredSize(d);
		t_phone.setPreferredSize(d);
		t_gender.setPreferredSize(d);
		
		// ADD 
		p_west.add(t_id);
		p_west.add(t_phone);
		p_west.add(t_gender);
		p_west.add(bt);
		
		add(p_west, BorderLayout.WEST);//패널을 서쪽에 부착 
		add(scroll); //센터에 스크롤 부착(테이블 포함된)
		
		// 버튼과 리스너 연결 
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
			
		// Window 설정 
		setSize(500,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void regist() {
		// 배열은 생성시 크기를 정해두면 차후 수정이 불가하다
		// 기존의 데이터 변수가 가리키고 있던 배열을 버리고 새로운 배열을 생성해 데이터에 대입
		
		// ArrayList에 새 멤버 추가
		String[] member = {
			t_id.getText(),
			t_phone.getText(),
			t_gender.getText()
		};
		
		model.list.add(member);
		System.out.println("현재 누적된 사원 수: " + model.list.size());
		
		// JTable에 데이터 갱신
		table.updateUI();
	}
	
	public static void main(String[] args) {
		new MemberRegist();
	}
}