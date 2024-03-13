package com.sds.SEShop.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sds.SEShop.admin.AdminList;
import com.sds.SEShop.admin.AdminRegist;
import com.sds.SEShop.admin.Login;
import com.sds.SEShop.product.ProductList;
import com.sds.SEShop.product.ProductRegist;

public class ShopMain extends JFrame{

	JPanel p_north;
	JButton bt_regist, bt_list, bt_admin, bt_join, bt_login;
	JPanel p_center; // 페이지들이 교체되며 디스플레이될 컨테이너

	// 상품 등록, 상품 목록, 관리자 등록, 관리자 목록, 로그인
	String[] iconArray = {"regist.png", "list.png", "join.png", "admin.png", "login.png"};
	// JS와 달리 자바의 순수 배열은 indexOf 등이 불가함
	// 이는 컬렉션 프레임워크에 존재
	// 아래의 배열을 collectionFramework에서 지원하는 객체로 변경
	//JButton[] btn = new JButton[iconArray.length];
	ArrayList<JButton> btn = new ArrayList<JButton>();
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "SESHOP";
	String pw = "1234";
	
	public Connection con; // 접속 이후 정보를 가진 객체
	
	// 페이지들
//	ProductRegist productRegist;
//	ProductList productList;
//	AdminRegist adminRegist;
//	AdminList adminList;
//	Login login;
	Page[] pages;
	
	public ShopMain() {
		p_north = new JPanel();
		Dimension d = new Dimension(75, 60);
		/*
		for(int i=0; i<btn.length; i++) {
			btn[i] = new JButton(getIcon(iconArray[i]));
			btn[i].setPreferredSize(d);
			p_north.add(btn[i]);
		}
		*/
		for(int i=0; i<iconArray.length; i++) {
			JButton bt = new JButton(getIcon(iconArray[i]));
			bt.setPreferredSize(d);
			p_north.add(bt);
			btn.add(bt);
		}

		 // 센터 프레임 생성
		p_center = new JPanel();
		// p_center.setBackground(Color.YELLOW);
		
		// 5개의 페이지 생성
		pages = new Page[5];
		pages[0] = new ProductRegist();
		pages[1] = new ProductList();
		pages[2] = new AdminRegist(this);
		pages[3] = new AdminList();
		pages[4] = new Login(this);
		
		// 로그인 페이지부터 보여주기
		showHide(pages.length-1);
		
		// 센터에 부착
		for(int i=0; i<pages.length; i++)
			p_center.add(pages[i]);
		
		// 프레임에 부착
		add(p_north, BorderLayout.NORTH);
		add(p_center); // BorderLayout.CENTER
		
		// 버튼에 리스너 연결
		for(int i=0; i<btn.size(); i++) {
			btn.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showHide(btn.indexOf(e.getSource()));
				}
			});
		}
		
		// Visualize
		setSize(1000, 850);
		setVisible(true);
		
		// Oracle 접속 시도
		connect();
		
		// Window adapter(리스너를 구현한 객체) 연결
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// DB 연결 끊기
				if(con != null)
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				// 프로세스 닫기
				System.exit(0);
			}
		});
		
	}
	
	// Oracle 접속
	public void connect() {
		this.setTitle("접속 시도중");
		try {
			// 드라이버 로드
			Class.forName(driver);
			this.setTitle("드라이버 로드 성공");
			
			// 접속 시도
			con = DriverManager.getConnection(url, user, pw);
			if(con == null)
				this.setTitle("접속 실패");
			else
				this.setTitle("Oracle 연결됨");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			this.setTitle("드라이버 확인 필요");
		} catch (SQLException e) {
			e.printStackTrace();
			this.setTitle("접속 실패");
		}
		this.setTitle("오라클 연결됨");
	}
	
	// 페이지 보여주기
	public void showHide(int index) {
		for(int i=0; i<pages.length; i++) {
			if(i == index)
				pages[i].setVisible(true);
			else
				pages[i].setVisible(false);
		}	
	}
	
	// 지정한 경로의 icon을 반환해 주는 메서드
	public ImageIcon getIcon(String filename) {
		// 패키지를 기준으로 자원 경로를 얻기
		URL url = this.getClass().getClassLoader().getResource(filename); // Class 에 대한 정보를 가진 클래스
		ImageIcon icon = new ImageIcon(url);
		// ImageIcon 클래스 자체에는 크기를 조정하는 메서드가 없음; Image로 변환 후 getScaledInstance()메서드로 크기 조정
		Image image= icon.getImage();
		image = image.getScaledInstance(75, 60, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	
	public static void main(String[] args) {
		new ShopMain();
	}
}
