package com.sds.newsapp.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sds.newsapp.news.News;
import com.sds.newsapp.news.NewsDAO;

public class WriteForm extends JFrame{
	JTextField t_title, t_writer;
	JTextArea area;
	JScrollPane scroll;
	JButton bt;
	
	News news;
	NewsDAO newsDAO;
	
	public WriteForm() {
		// 생성		
		t_title = new JTextField();
		t_writer = new JTextField();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt = new JButton("등록");
		newsDAO = new NewsDAO();
		
		// Style
		Dimension d = new Dimension(280, 40);
		t_title.setPreferredSize(d);
		t_writer.setPreferredSize(d);
		scroll.setPreferredSize(new Dimension(280, 200));
		
		// Add
		setLayout(new FlowLayout());
		add(t_title);
		add(t_writer);
		add(scroll);
		add(bt);
		
		// 버튼과 리스너 연결
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				News news = new News();
				
				news.setTitle(t_title.getText());
				news.setWriter(t_writer.getText());
				news.setContent(area.getText());
				
				int result = newsDAO.insert(news);
				
				if(result>0)
					JOptionPane.showMessageDialog(WriteForm.this, "등록 성공");
				else
					JOptionPane.showMessageDialog(WriteForm.this, "등록 실패");
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new WriteForm();
	}

}
