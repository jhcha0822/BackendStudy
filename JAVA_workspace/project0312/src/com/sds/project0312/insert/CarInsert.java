package com.sds.project0312.insert;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

// mySQL 테이블에 데이터 넣어보기
// 드라이버: 프로그래밍 언어가 DBMS 제품을 제어하기 위한 소프트웨어
// DBMS 제조사가 개발자들에게 제공할 의무가 있음
// mySQL 제조사에서 드라이버 다운로드

// MySQL 가동하기
// 환경변수 path에 ~/bin 까지 등록
// cmd 창에서 mysqld.exe 가동프로그램으로 서버 시작
// 새 cmd 창에서 mysql -h localhost -u root

//create database javase;
//use javase;
//create table car(
//car_idx int primary key auto_increment
//, name varchar(20)
//, price int default 0
//);


public class CarInsert extends JFrame {
	JButton bt_connect;
	JTextField t_name;
	JTextField t_price;
	JButton bt_regist;
	
	// DB정보는 노출되지 말아야 함
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/javase"; // 암기
	String id = "root";
	String pw = "";
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String id = "wendy";
//	String pw = "1234";
	
	Connection con = null;	// 접속 정보를 가진 객체
	
	public CarInsert() {
		// 생성
		bt_connect = new JButton("접속");
		t_name = new JTextField();
		t_price = new JTextField();
		bt_regist = new  JButton("등록");
		
		// Style
		Dimension d = new Dimension(370, 38);
		t_name.setPreferredSize(d);
		t_price.setPreferredSize(d);
		
		//조립
		setLayout(new FlowLayout());
		add(bt_connect);
		add(t_name);
		add(t_price);
		add(bt_regist);
		
		// 접속 버튼과 리스너 연결
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		// 등록 버튼과 리스너 연결
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
		
		// 현재 창에 윈도우 리스너 연결
		this.addWindowListener(new WindowAdapter() { // 어탭터
			// 대신 이벤트 관련 리스너를 구현한 객체를 가리켜 어댑터라 함
			// 어댑터는 인터페이스의 추상메서드들을 재정의해놓았기에 그 중 원하는 것만 오버라이드해 사용 가능
			public void windowClosing(WindowEvent e) {
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				// 현재 프로세스도 종료
				System.exit(0);
			}
		});
		
		setSize(400,250);
		setVisible(true);
	}
	
	public void connect() {
		try {
			// 드라이버 로드
			Class.forName(driver);
			this.setTitle("드라이버 로드 성공");
			
			// 접속
			con = DriverManager.getConnection(url, id, pw);
			if(con == null)
				this.setTitle("접속 실패");
			else
				this.setTitle("접속 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			this.setTitle("드라이버 로드 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			this.setTitle("접속 실패");
		}
	}
	
	// 등록: 네트워크를 통해 준비된 쿼리문 전송
	public void regist() {
		java.sql.PreparedStatement pstmt = null; // 쿼리문 담당 객체
		
		// 쿼리문은 접속이 성공해야 실행이 가능하기에
		// 접속 정보를 가진 Connection 객체로부터 인스턴스를 받아온다
		/*
		String sql = "insert into car(car_idx, name, price)"; // Oracle
		sql += " values(seq_car.nextval, '"+t_name.getText()+"', "+t_price.getText()+")";
		*/
		
		String sql = "insert into car(name, price)"; // MySQL
		sql += "values('"+t_name.getText()+"', "+t_price.getText()+")";
		
		try {
			pstmt = con.prepareStatement(sql); // 쿼리문 준비
			int result = pstmt.executeUpdate(); // 준비된 쿼리문 수행
			if(result < 1)
				this.setTitle("입력 실패");
			else
				this.setTitle("입력 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			this.setTitle("입력 실패");
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new CarInsert();
	}
}
