package com.sds.SEShop.main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

// 하위 모든 페이제에 공통적인 코드 발견
// 유지보스를 위해 최상위 객체 정의
// 중복되고 보편적인 코드면 이 클래스에 작성
public class Page extends JPanel{
	protected JPanel container; // 자식이 보유
	protected ShopMain shopMain;
	
	// 호출할 시 Color의 인스턴스도 전달 필요, ex) super(Color.RED)
	public Page(Color color) {
		this.setBackground(color);
		this.setPreferredSize(new Dimension(1000, 800));
	}
}
