package com.sds.newsapp.map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sds.newsapp.news.News;

// DAO: store 테이블에 대한 CRUD 
public class StoreDAO {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "seshop";
	String pw = "1234";
	
	// 등록
	public int insert(Store store) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			Class.forName(driver);
			
			String sql = "insert into store(store_idx, name, lati, longi)";
			sql += " values(seq_store.nextval, ?, ?, ?)";
			
			con = DriverManager.getConnection(url, user, pw);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, store.getName());
			pstmt.setDouble(2, store.getLati());
			pstmt.setDouble(3, store.getLongi());
			
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}		
		return result;
	}
	
	// 목록
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Store> list = new ArrayList<Store>(); // DTO를 담을 list
		
		try {
			Class.forName(driver);
			
			String sql = "select * from store";
			
			con = DriverManager.getConnection(url, user, pw);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				// 한건의 레코드를 담게 될 빈 DTO 생성
				Store store = new Store();
				store.setName(rs.getString("name"));
				store.setLati(rs.getDouble("lati"));
				store.setLongi(rs.getDouble("longi"));
				store.setStore_idx(rs.getInt("store_idx"));
				list.add(store);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
}
