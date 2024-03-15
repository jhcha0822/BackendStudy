package com.sds.SEShop.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sds.SEShop.main.Page;
import com.sds.SEShop.main.ShopMain;

public class ProductList extends Page{
	public JTable table;
	JScrollPane scroll;
	JPanel p_south;
	
	JPanel p_preview; // 상세보기 이미지
	JLabel la_product_name, la_price, la_brand;
	
	ProductTableModel model;
	Image image;
		
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
		p_preview = new JPanel() {
			// 내부익명클래스
			public void paint(Graphics g) {
				g.setColor(Color.YELLOW);
				g.fillRect(0, 0, 350, 280);
				g.drawImage(image, 0, 0, 350, 280, p_preview);
			}
			
		};
		la_product_name = new JLabel();
		la_price = new JLabel();
		la_brand = new JLabel();
		
		// Style
		p_south.setPreferredSize(new Dimension(800, 250));
		p_south.setBackground(Color.YELLOW);
		p_preview.setPreferredSize(new Dimension(350, 280));
		Dimension d = new Dimension(290, 40);
		la_product_name.setPreferredSize(d);
		la_price.setPreferredSize(d);
		la_brand.setPreferredSize(d);
		
		// ADD
		p_south.add(p_preview);
		p_south.add(la_product_name);
		p_south.add(la_price);
		p_south.add(la_brand);
		
		
		container.add(scroll);
		container.add(p_south, BorderLayout.SOUTH);
		
		add(container);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				String value = (String)table.getValueAt(row, 0); // idx
				// System.out.println(row+", "+col+": "+value);
				getProductDetail(Integer.parseInt(value)); // 상품 한 건의 상세정보 가져오기
			}
		});
	}
	
	// 상품 한 건의 상세정보 가져오기
	public void getProductDetail(int product_idx) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from product where product_idx="+product_idx;
		try {
			pstmt = shopMain.con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // select문 수행 결과 반환
			
			// rs의 값을 상세보기 패널에 반영하자
			rs.next(); // 커서 1칸 전진
			String filename = "D:\\MULTICAMPUS\\JAVA_workspace\\SEShop\\res\\"+rs.getString("filename");
			ImageIcon icon = new ImageIcon(filename);
			image = icon.getImage();
			p_preview.repaint();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	// 상품 가져오기
	public void getProductList() {
		PreparedStatement pstmt = null; // 쿼리 실행 객체
		ResultSet rs = null; // 표를 표현한 객체
		
		String sql = "select * from product order by product_idx asc"; // 오름차순
		
		try {
			pstmt = shopMain.con.prepareStatement(sql); // 쿼리 실행 객체 생성
			rs = pstmt.executeQuery(); // 쿼리 실행 후 결과 받아오기
			model.list.removeAll(model.list); // 기존의 컨트롤러가 보유한 리스트를 비우기
			while(rs.next()) { // 커서 한칸 전진
				String[] record = new String[6];
				record[0] = Integer.toString(rs.getInt("product_idx"));
				record[1] = rs.getString("product_name");
				record[2] = Integer.toString(rs.getInt("price"));
				record[3] = rs.getString("brand");
				record[4] = rs.getString("filename");
				record[5] = Integer.toString(rs.getInt("subcategory_idx"));
				model.list.add(record);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
}
