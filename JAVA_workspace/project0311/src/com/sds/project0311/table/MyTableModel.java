package com.sds.project0311.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

// 보여지는 부분인 JTable(View)과 그를 처리하는 로직 Model(Data) 영역을 분리시켜 주는 역할을 수행하는 (Controller의 역할을 수행하는)
// AbstractTableModel을 이용해보기
public class MyTableModel extends AbstractTableModel{

 	// 데이터가 되는 이차원 배열
	// 자바는 배열의 길이가 고정되므로, 데이터가 추후 지속적인 변경사항이 발생한다면
	// 보다 유연한 컬렉션프레임워크를 이용한다
	// 자바에서 객체만을 모아서 처리할 때 유용한 기능을 지원하는 패키지
	// 아래는 인터페이스이기에 이를 상속받는 하위 자식을 이용함
	// 1) List형: 순서있는 집합을 표현한 객체
	//              배열과 거의 같으나 길이가 유동적이고 객체만을 담을 수 있다
	// 2) Set형: 순서없는 집합을 표현한 객체
	// 3) Map형: key-value쌍으로 이루어진 집합을 표현한 객체
	
	// 배열을 대체할 보다 유연한 Collection Framework의 List의 자식 중 하나인 ArrayList
	ArrayList list = new ArrayList();
	
	String[] column= {"아이디","연락처","성별"}; 
	
	public MyTableModel() {
		// 생성자, 데이터 초기화 진행
		// list에 한 사람의 정보를 삽입
		String[] member = {"Doe", "xxx", "Male"};
		list.add(member);
	}
	
	public int getRowCount() {
		// return data.length;
		return list.size();
	}

	public int getColumnCount() {
		return column.length;
	}

	// 재정의된 모든 메서드는 JTable을 위한 것: JTable이 데이터를 보여주기 위한 목적으로 호출하는 메서드
	// 아래의 getValueAt()메서드는 getRowCount()*getColumnCount 반환 값 만큼 호출
	public Object getValueAt(int row, int col) {
		// return data[row][col];
		Object obj = list.get(row);
		String[] member = (String[])obj;
		return member[col];
	}
	
	// Column의 이름을 반환하는 메서드
	// getColumnCount() 메서드에서 반환된 값 만큼 0부터 1씩 증가하며 반복 호출
	public String getColumnName(int col) {
		return column[col];
	}
	
}
