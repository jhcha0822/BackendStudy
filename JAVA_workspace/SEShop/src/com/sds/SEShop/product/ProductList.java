package com.sds.SEShop.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sds.SEShop.main.Page;
import com.sds.SEShop.main.ShopMain;

public class ProductList extends Page{
	JTable table;
	JScrollPane scroll;
	JPanel p_south;
	ProductTableModel model;
	
	public ProductList(ShopMain shopMain) {
		super(Color.RED);
		this.shopMain = shopMain;
		
		// 컨테이너 올리기
		container = new JPanel();
		container.setPreferredSize(new Dimension(800, 700));
		container.setBackground(Color.orange);
		container.setLayout(new BorderLayout()); //flow -> border
		
		// 생성
		table = new JTable(model = new ProductTableModel());
		scroll = new JScrollPane(table);
		p_south = new JPanel();
		
		// Style
		p_south.setPreferredSize(new Dimension(800, 250));
		p_south.setBackground(Color.YELLOW);
		
		// ADD
		container.add(scroll);
		container.add(p_south, BorderLayout.SOUTH);
		
		add(container);
	}
}
