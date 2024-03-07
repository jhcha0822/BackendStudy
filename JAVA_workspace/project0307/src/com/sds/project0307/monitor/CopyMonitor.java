package com.sds.project0307.monitor;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;

// 파일을 복사하는 과정에서 실행중인 프로그램으로 들어오고 나가는 바이트들을 육안으로 확인해보기
public class CopyMonitor extends JFrame implements ActionListener, WindowListener{
	JLabel la_ori, la_dest;
	JTextField t_ori, t_dest;
	JTextArea area;
	JButton bt;
	FileInputStream fis;
	FileOutputStream fos;

	public CopyMonitor() {
		super("데이터 복사 모니터링"); // 부모 JFrame 생성자 호출
		la_ori = new JLabel("원본 경로");
		la_dest = new JLabel("복사 경로");
		
		t_ori = new JTextField("D:\\MULTICAMPUS\\JAVA_workspace\\project0307\\res\\hello.txt");
		t_dest = new JTextField("D:\\MULTICAMPUS\\JAVA_workspace\\project0307\\res\\hello2.txt");
		
		area = new JTextArea();
		
		bt = new JButton("복사");
		
		// Style
		Dimension d1 = new Dimension(150, 35); // JLabel
		Dimension d2 = new Dimension(620, 35); // JTextField
		Dimension d3 = new Dimension(750, 350); // JTextArea
		
		la_ori.setPreferredSize(d1);
		la_dest.setPreferredSize(d1);
		t_ori.setPreferredSize(d2);
		t_dest.setPreferredSize(d2);
		area.setPreferredSize(d3);
		
		// Append
		setLayout(new FlowLayout());
		add(la_ori);
		add(t_ori);
		add(la_dest);
		add(t_dest);
		add(area);
		add(bt);
		
		// 리스너 연결
		bt.addActionListener(this);      // 버튼과 리스너 연결
		this.addWindowListener(this); // 윈도우와 리스너 연결
		
		// Window
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void copy() {
		try {
			fis = new FileInputStream(t_ori.getText());
			fos = new FileOutputStream(t_dest.getText());
			this.setTitle("스트림 생성 성공");
			
			int data = -1;
			while(true) {
				data = fis.read();
				if(data == -1)
					break;
				fos.write(data);
				
				// area에 현재 프로그램을 거쳐가는 바이트 출력
				// java의 모든 기본자료형마다 1:1 대응되는 Wrapper클래스가 지원됨
				// Wrapper: 기본자료형과 객체자료형간 변환
				// ex) int형 3을 객체형인 "3" String으로 변환하거나 그 역
				// String str = Integer.toString(data);
				// area.append(str);
				System.out.println(data);
				
				// 한글은 2바이트기 때문에 하나씩 읽어와서 출력할 경우
				// 첫 바이트만을 출력하기 때문에 (char)
				// 한글이 깨진다
				// 복사는 문제가 없다
			}
			JOptionPane.showMessageDialog(this, "복사완료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			this.setTitle("스트림 생성 실패");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		copy();		
	}
	
	public void windowClosing(WindowEvent e) {
		if(fis != null) {
			try {
				fis.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(fos != null) {
			try {
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new CopyMonitor();
	}
	
	public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}
