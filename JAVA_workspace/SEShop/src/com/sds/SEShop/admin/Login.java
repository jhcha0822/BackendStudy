package com.sds.SEShop.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sds.SEShop.main.Page;
import com.sds.SEShop.main.ShopMain;

public class Login extends Page{
	
	ShopMain shopMain; // null, 생성자로 주소 입력
	
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
		
	}
}
