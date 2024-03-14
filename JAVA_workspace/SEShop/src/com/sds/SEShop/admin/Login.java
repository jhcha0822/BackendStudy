package com.sds.SEShop.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sds.SEShop.lib.EncryptionManager;
import com.sds.SEShop.main.Page;
import com.sds.SEShop.main.ShopMain;

public class Login extends Page{
		
	// JPanel container;
	JLabel la_id, la_pw;
	JTextField t_id;
	JPasswordField t_pw;
	JButton bt_login, bt_regist;
	
	public Login(ShopMain shopMain) {
		super(Color.MAGENTA);
		
		this.shopMain = shopMain;
				
		// 생성
		container = new JPanel();
		la_id = new JLabel("ID");
		la_pw = new JLabel("PW");
		t_id = new JTextField();
		t_pw = new JPasswordField();
		bt_login = new JButton("Login");
		bt_regist = new JButton("Register");
		
		// Style
		container.setPreferredSize(new Dimension(600, 250));
		container.setBackground(Color.GRAY);
		//container.setVisible(true); 없어도 보임
		Dimension d = new Dimension(280, 40);
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		la_pw.setPreferredSize(d);
		t_pw.setPreferredSize(d);
		
		// Add
		container.add(la_id);
		container.add(t_id);
		container.add(la_pw);
		container.add(t_pw);
		container.add(bt_login);
		container.add(bt_regist);
		add(container);
		
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 회원가입 페이지로 전환: Shopmain의 showhide
				shopMain.showHide(2);
			}
		});
		
		bt_login.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				loginCheck();
			}
		});
		
	}
	
	// 로그인 폼에 작성한 데이터와 오라클 비교해
	// 레코드 존재시 관리자로 인정
	public void loginCheck() {
		PreparedStatement pstmt = null; // 쿼리 수행 인터페이스 객체
		ResultSet rs = null; // select문 수행 후 결과를 담기 위한 인터페이스 객체
								   // try-catch 밖에 있어야 finally에서 닫기 가능
		String id=t_id.getText(); // 유저가 입력한 id 값
		String pass=new String(t_pw.getPassword());  // 유저가 입력한 패스워드 값
		pass = EncryptionManager.getConvertedData(pass);// 평문을 암호화된 해시값로 변경
		String sql="select * from admin where id='"+id+"' and pass='"+pass+"' ";
		try {
			pstmt = shopMain.con.prepareStatement(sql); // 쿼리준비
			// 쿼리 실행 executeUpdate(dml), executeQuery(select): ResultSet 표를 반환 
			rs = pstmt.executeQuery(); // 레코드 존재할때 select문 수행 후 표 반환
			
			// 아이디와 패스워드가 일치하는 레코드는 오직 1건
			// 레코드 존재시 rs.next() 호출: true 반환 (커서 이동 가능)
			boolean result = rs.next();
			if(result) {
				JOptionPane.showMessageDialog(this, "인증 성공");
				shopMain.loginflag = true;
			}
			else {
				JOptionPane.showMessageDialog(this, "인증 실패");
				shopMain.loginflag = false;
			}
			// 프레임에 메시지 출력
			shopMain.setCurrentTitle(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
