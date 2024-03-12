package com.sds.project0312.select;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

// 유지보수성을 높이기 위한 개발방법 이론인 MVC 패턴에 의하면
// 디자인 영역과 데이터 및 데이터 제어 모델 영역은 분리시켜 개발해야 함
// JTable(VIEW) -- Controller(TableModel) -- 데이터, ArrayList (model)
public class MyTableModel extends AbstractTableModel{
	// TableModel 객체가 제공해주는 모든 메서드는 JTable을 위한 메서드임
	
	ArrayList<String[]> list = new ArrayList<String[]>(); // size 0 인 비어있는 리스트
	
	String[] column = {"car_idx", "name", "price"};
	
	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return column.length;
	}
	
	public String getColumnName(int col) {
		return column[col];
	}

	public Object getValueAt(int row, int col) {
		return list.get(row)[col];
	}
}
