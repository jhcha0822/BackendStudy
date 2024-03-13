package com.sds.SEShop.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sds.SEShop.lib.EncryptionManager;
import com.sds.SEShop.lib.FileManager;
import com.sds.SEShop.main.Page;
import com.sds.SEShop.main.ShopMain;

public class AdminRegist extends Page{
	
	JLabel la_id, la_pw, la_email, la_profile, la_dummy;
	JTextField t_id, t_email;
	JPasswordField t_pw;
	JButton bt_profile, bt_regist, bt_login; // 프로필 사진 선택 창 띄우는 버튼
	JPanel p_preview;
	
	JFileChooser chooser; // 파일 탐색기
	Image image = null; // 선택 안한 이미지 null
	File file = null;
	String myName;
	
	public AdminRegist(ShopMain shopMain) {		
		super(Color.CYAN);
		
		this.shopMain = shopMain;
		
		container = new JPanel();
		la_id = new JLabel("ID");
		la_pw = new JLabel("PW");
		la_email = new JLabel("Email");
		
		t_id = new JTextField();
		t_pw = new JPasswordField();
		t_email = new JTextField();
		
		bt_profile = new JButton("파일 찾기");
		p_preview = new JPanel() {
			// p_preview 재정의를 위해 내부 익명 클래스 선언
			public void paint(Graphics g) {
				g.setColor(Color.CYAN);
				g.fillRect(0, 0, 280, 280);
				if(image == null) {
					g.setColor(Color.PINK);
					g.drawString("파일 선택", 40, 40);
				}
				g.drawImage(image, 0, 0, 280, 280, p_preview);
			}
		};
		la_profile = new JLabel("프로필");
		la_dummy = new JLabel();
		bt_regist = new JButton("가입");
		bt_login = new JButton("로그인");
		
		chooser = new JFileChooser("D:\\MULTICAMPUS\\JavaScript_workspace\\images\\redvelvet");
		
		// Style
		container.setPreferredSize(new Dimension(600, 700));
		container.setBackground(Color.LIGHT_GRAY);
		
		Dimension d = new Dimension(280, 40);
		
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		la_pw.setPreferredSize(d);
		t_pw.setPreferredSize(d);
		la_email.setPreferredSize(d);
		t_email.setPreferredSize(d);
		la_profile.setPreferredSize(d);
		bt_profile.setPreferredSize(d);
		la_dummy.setPreferredSize(new Dimension(280, 280));
		p_preview.setPreferredSize(new Dimension(280, 280));
		
		// ADD
		container.add(la_id);
		container.add(t_id);
		container.add(la_pw);
		container.add(t_pw);
		container.add(la_email);
		container.add(t_email);
		container.add(la_profile);
		container.add(bt_profile);
		container.add(la_dummy);
		container.add(p_preview);
		container.add(bt_regist);
		container.add(bt_login);
		
		add(container);
		
		// 리스너 연결
		bt_profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preview();
			}
		});
		
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
		
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopMain.showHide(4);
			}
		});
	}
	
	// 프로필 파일 열어 이미지를 패널에 부착
	public void preview() {
		int result = chooser.showOpenDialog(this);
		if(result == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile(); // 유저가 선택한 파일
			String filename = file.getAbsolutePath(); // 파일의 경로			
			
			String ext = FileManager.getExt(filename); // 확장자
			long time = System.currentTimeMillis(); // 파일명으로 사용할 날짜 ms까지 얻기
			myName = time + "." + ext;
			
			ImageIcon icon = new ImageIcon(filename);
			image = icon.getImage(); // 멤버변수인 이미지에 대입
			// 미리보기 패널에게 다시 그릴것을 명령
			p_preview.repaint();
		}
	}
	
	// 파일 복사
	public void copy() {
		// 원본 파일인 filename과 연관된 입력스트림을 연결하고 바이트 데이터를 이용해
		// 비어 있는 myName 파일에 내려 쓰기 >> 파일복사
		FileInputStream fis = null; // 파일을 대상으로 한 바이트 스트림
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream("D:\\MULTICAMPUS\\JAVA_workspace\\SEShop\\res\\"+myName);
			System.out.println("스트림 생성 성공");
			// 입력스트림으로 1바이트씩 읽어 출력스트림으로 1바이트씩 작성
			int data = -1;
			while(true) {	
				data = fis.read();
				if(data == -1)
					break;
				fos.write(data);
			}
			JOptionPane.showMessageDialog(this, "파일이 로컬에 보관되었습니다.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("스트림 생성 실패");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	// 회원가입 메서드
	public void regist() {
		// 쿼리문 수행
		PreparedStatement pstmt = null; // 쿼리문 수행은 접속 이후에 가능; 접속객체 요구
		
		// ShopMain의 con은 다른 패키지에 있는 멤버변수: default
		// 접근 제한자 public 혹은 getter 메서드 필요
		try {
			String id = t_id.getText();
			String pass = new String(t_pw.getPassword());
			String email = t_email.getText();
			// String filename = file.getAbsolutePath(); // 선택한 파일의 전체 디렉토리
						
			pass = EncryptionManager.getConvertedData(pass); // 암호화
			
			String sql = "insert into admin(admin_idx, id, pass, email, filename)";
			sql += " values(seq_admin.nextval, '"+id+"', '"+pass+"', '"+email+"', '"+myName+"')";
			
			pstmt = shopMain.con.prepareStatement(sql);
			
			// 쿼리문 수행
			int result = pstmt.executeUpdate(); // DML인 경우는 0 이상의 값이 나와야 성공
			if(result == 0)
				JOptionPane.showMessageDialog(this, "가입 실패");
			else {
				copy();
				JOptionPane.showMessageDialog(this, "가입 성공\n로그인 하여 사용하세요");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
