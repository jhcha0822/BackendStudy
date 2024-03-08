package com.sds.project0307.editor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// 메모장
public class MemoApp extends JFrame implements ActionListener{
	JMenuBar bar; // 메뉴들을 올려놓을 막대기 (파일, 편집 ...)
	JMenu m_file; // 파일
	JMenu m_edit; // 편집
	JMenu m_style; // 서식
	JMenu m_view; // 보기
	JMenu m_help; // 도움말
	
	// 파일에 소속된 메뉴 아이템들
	String[] itemTitle = {"새로만들기", "새창", "열기", "저장", "다른 이름으로 저장", "페이지 설정", "인쇄", "끝내기"};
	JMenuItem[] item = new JMenuItem[itemTitle.length];
	JTextArea area;
	JScrollPane scroll;
	
	File file; // 열어둔 파일
	FileInputStream fis; // 바이트기반 스트림
	InputStreamReader reader; // 문자기반 입력 스트림
	BufferedReader buffr;
	
	FileWriter fw; // 문자 기반의 출력 스트림
	
	JFileChooser chooser; // 자바에서 파일 탐색기를 가리켜 JFileChooser라 함	
	
	public MemoApp() {
		bar = new JMenuBar();
		m_file = new JMenu("파일");
		m_edit = new JMenu("편집");
		m_style = new JMenu("서식");
		m_view = new JMenu("보기");
		m_help = new JMenu("도움말");
		
		for(int i=0; i<itemTitle.length; i++) {
			item[i] = new JMenuItem(itemTitle[i]);
			// 파일 메뉴에 부착
			m_file.add(item[i]);
			if(i==4 || i==6)
				m_file.addSeparator(); // 구분선
			// 메뉴 아이템들과 리스너 연결
			item[i].addActionListener(this);
		}
		
		// 파일 탐색기를 생성
		// 메서드를 호출하면 창이 띄워짐
		chooser = new JFileChooser("D:\\MULTICAMPUS\\JAVA_workspace\\project0307\\src\\com\\sds\\project0307");
		
		// 메뉴를 바에 붙이기
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);

		// 바는 원하는 위치에 임의로 둘 수 없고 그 위치가 이미 정해져 있다.
		this.setJMenuBar(bar); // 프레임의 메서드로 위치 고정
		
		area = new JTextArea();
		
		// 스크롤을 적용하고 싶은 컴포넌트를 생성자의 인수로 전달
		scroll = new JScrollPane(area); // area에 스크롤이 달림
		add(scroll);
		
		// area의 폰트 크기 조절
		area.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		
		// Window
		setSize(1400, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MemoApp();
	}
	
	// 파일 열기
	public void openFile() {
		// 문서 파일을 대상으로 입력받기
		// 파일 열기 탐색기를 띄워야 함
		int result = chooser.showOpenDialog(this); // 새 창은 부모컨테이너가 존재해야 뜰 수 있음, 단독으로 사용 불가
		// result: open --> 0, cancel --> 1
		// APPROVE_OPTION (0), CANCEL_OPTION (1) 상수를 이용하자
		if(result == JFileChooser.APPROVE_OPTION) {
			// 유저가 선택한 파일이 무엇인지 파악
			// 자바에서는 디렉토리와 파일 모두 File 인스턴스로 취급
			File file = chooser.getSelectedFile();
			try {
				fis = new FileInputStream(file); // 파일객체로 스트림 생성
				reader = new InputStreamReader(fis); // 문자 단위 입력
				buffr = new BufferedReader(reader); // 버퍼로 읽기
						
				// int data = -1; // 바이트, 문자 단위 입력
				String data = null;
				int count = 0;
				
				while(true) {
					//data = fis.read();
					//data = reader.read();
					data = buffr.readLine();
					count++;
					if(data == null)
					//if(data == -1)
						break;
					area.append(""+data+"\n");
					//area.append(""+(char)data);
				}
				// 윈도우창 제목에 읽어들인 횟수 출력
				this.setTitle("읽은 횟수 "+count);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(buffr != null) {
					try {
						buffr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void saveFile() {
		// 열어놓은 파일을 대상으로 출력스트림 생성
		try {
			fw = new FileWriter(file);
			fw.write(area.getText());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public void actionPerformed(ActionEvent e) {
		// 이벤트를 일으킨 컴포넌트를 이벤트 소스라 함
		// e객체로부터 소스를 얻어오기
		Object obj = e.getSource();
		if(obj == item[2]) // 열기
			openFile();
		else if(obj == item[3]) // 저장
			saveFile();
	}
}
