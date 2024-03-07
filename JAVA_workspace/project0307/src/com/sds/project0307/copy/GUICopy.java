package com.sds.project0307.copy;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUICopy extends JFrame implements ActionListener, WindowListener{
	JLabel la_ori, la_dest;
	JTextField t_ori, t_dest;
	JButton bt;
	// 필요한 객체가 있다면 has-a 로 포함
	FileInputStream fis;
	FileOutputStream fos;
	
	public GUICopy() {
		super("파일 복사 프로그램");
		
		la_ori = new JLabel("원본 위치");
		la_dest = new JLabel("복사본 위치");
		
		t_ori = new JTextField("D:/MULTICAMPUS/JAVA_workspace/project0307/res/chicken.png");
		t_dest = new JTextField("D:/MULTICAMPUS/JAVA_workspace/project0307/res/chicken2.png");
		
		bt = new JButton("복사");
		
		// Style
		Dimension d1 = new Dimension(180, 35);
		Dimension d2 = new Dimension(480, 35);
		
		la_ori.setPreferredSize(d1);
		la_dest.setPreferredSize(d1);
		
		t_ori.setPreferredSize(d2);
		t_dest.setPreferredSize(d2);
				
		// Append
		this.setLayout(new FlowLayout());
		add(la_ori);
		add(t_ori);
		add(la_dest);
		add(t_dest);
		add(bt);
		
		// 리스너와 연결
		bt.addActionListener(this);
		this.addWindowListener(this); // 윈도우와 리스너 연결
		
		//Window
		this.setSize(700, 160);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		// stream과 같이 사용 후 메모리에서 해제시켜야 할 자원이 있는 경우
		// 아래의 프로세스 종료는 위험하다. 스트림이 안닫히기 때문
		// this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void copy() {
		// 원본 파일에 istream 생성
		
		// try-catch 단축키: 블럭 지정 후 shift+alt+z
		try {
			fis = new FileInputStream(t_ori.getText());
			fos = new FileOutputStream(t_ori.getText());
			this.setTitle("파일을 대상으로 스트림 생성 성공");
			// 생선된 스트림들을 이용하여 read, write
			int data = -1;
			while(true) {
				data = fis.read(); // 1바이트 읽기
				if(data == -1)
					break;
				fos.write(data);   // 비어 있던 파일에 출력
			}
			JOptionPane.showMessageDialog(this, "파일 복사 완료");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "파일의 경로를 확인해주세요.");
			e.printStackTrace();
		} catch (IOException e) {
			this.setTitle("파일 입출력 에러 발생");
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		copy();		
	}
	
	public static void main(String[] args) {
		new GUICopy();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if(fis != null) {
			try {
				fis.close();
			} catch(IOException e1) {
				e1.printStackTrace();
			}
		}
		if(fos != null) {
			try {
				fos.close();
			} catch(IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}}
