package com.sds.project0311.table;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

// 자바에서 데이터를 표의 구조로 보여주는 컴포넌트인 JTable 학습
public class TableTest extends JFrame {
	JTable table;
	JScrollPane scroll;
	
	String[][] data = { //new String[4][4];
			{"A", "B", "C", "D"},
			{"가", "나", "다", "라"},
			{"1", "2", "3", "4"},
			{"a", "b", "c", "d"}
	};
	String[] cols = {"컬럼1", "컬럼2", "컬럼3", "컬럼4"};
	
	public TableTest() {
		// table = new JTable(5, 4);
		table = new JTable(data, cols);
		scroll = new JScrollPane(table);
		
		table.setPreferredSize(new Dimension(400, 400));
				
		// setLayout(new FlowLayout());
		// add(table); // BorderLayout.CENTER
		add(scroll);
		
		setSize(500, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TableTest();
	}
}
