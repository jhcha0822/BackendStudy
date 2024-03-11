package com.sds.project0311.table;

import javax.swing.table.AbstractTableModel;

// 보여지는 부분인 JTable(View)과 그를 처리하는 로직 Model(Data) 영역을 분리시켜 주는 역할을 수행하는 (Controller의 역할을 수행하는)
// AbstractTableModel을 이용해보기
public class FruitModel extends AbstractTableModel{


 	//데이터가 되는 이차원 배열 
	String[][] data={
		{"배", "나주", "4000"},
		{"사과", "문경", "2000"},
		{"딸기", "논산", "20000"}
	};
	String[] column= {"과일", "원산지", "가격"}; 
	
	
	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return column.length;
	}

	// 재정의된 모든 메서드는 JTable을 위한 것: JTable이 데이터를 보여주기 위한 목적으로 호출하는 메서드
	// 아래의 getValueAt()메서드는 getRowCount()*getColumnCount 반환 값 만큼 호출
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
}
