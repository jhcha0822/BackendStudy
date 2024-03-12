package com.sds.project0312.select;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CarSelect extends JFrame{

	JButton bt_connect;
	JButton bt_select;
	JPanel p_north; // 버튼 2개가 올려질 북쪽 패널
	JTable table;
	JScrollPane scroll;
	
//	String driver = "com.mysql.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/javase";
//	String user = "root";
//	String pw = "";
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "wendy";
	String pw = "1234";

	Connection con = null;
	MyTableModel model;
	
	public CarSelect() {
		bt_connect = new JButton("Connect");
		bt_select = new JButton("Load");
		p_north = new JPanel();
		table = new JTable(model = new MyTableModel()); // 컨트롤러
		scroll = new JScrollPane(table);
		
		// Add
		p_north.add(bt_connect);
		p_north.add(bt_select);
		add(p_north, BorderLayout.NORTH);
		
		// Center
		add(scroll);
		
		//접속버튼과 리스너 연결
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		//조회 버튼과 리스너 연결 
		bt_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		
		// 프레임에 리스너 연결
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// 데이터베이스 해제
				if(con != null)
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				// 프로세스 종료
				System.exit(0);	
			}
		});
		
		// Window
		setSize(600, 400);
		setVisible(true);
	}
	
	public void connect() {
		// Driver Load
		try {
			Class.forName(driver);
			this.setTitle("드라이버 로드 성공");
			// Connect
			con = DriverManager.getConnection(url, user, pw);
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
	
	public void load() {
		// 원격지에 연결된 데이터베이스 접속 정보인 Connection 객체를 이용하여
		// 쿼리문 객체를 생성한 후, 네트워크로 select 쿼리를 전송하고 그 결과도 가져오기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 접속된 이루에 쿼리 실행 가능 -> Connection 객체로부터 PreparedStatement 가져오기
		String sql = "select * from car order by car_idx asc";
		try {
			pstmt = con.prepareStatement(sql); // 쿼리 객체 생성
			// DML인 경우 executeUpdate() 메서드 사용, select문인 경우 executeQuery()
			rs = pstmt.executeQuery(); // 오라클의 표를 담고 있는 인터페이스
			
			// DB에서 레코드 가져오기 전에 기존의 list 내 데이터를 제거
			model.list.removeAll(model.list);
			
			// rs는 최초에 아무런 열도 가리키고 있지 않으며 가장 꼭대기에 존재 (첫 레코드 바로 위)
			while(rs.next()) { // 한칸 뒤가 존재할때, 그 칸에 대해
				// 비어있는 3개 요소의 String 배열을 생성해 한 층의 데이터를 담기
				String[] data = new String[3];
				data[0] = Integer.toString(rs.getInt("car_idx"));
				data[1] = rs.getString("name");
				data[2] = Integer.toString(rs.getInt("price"));
				
				// Add
				model.list.add(data);
				// System.out.println("현재까지 레코드 수: "+model.list.size());
				
				// JTable 갱신해 레코드 가져오기
				table.updateUI();
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		new CarSelect();
	}
}
