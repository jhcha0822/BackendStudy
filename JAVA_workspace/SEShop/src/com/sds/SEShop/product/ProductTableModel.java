package com.sds.SEShop.product;

import javax.swing.table.AbstractTableModel;

//상품 목록페이지의 JTable 이 바라보고 있는 컨트롤러 객체 
public class ProductTableModel extends AbstractTableModel{

	public int getRowCount() {
		return 10;
	}

	public int getColumnCount() {
		return 6;
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		return "가디건";
	}
	
}