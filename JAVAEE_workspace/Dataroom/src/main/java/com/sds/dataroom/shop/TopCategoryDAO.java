package com.sds.dataroom.shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sds.dataroom.common.PoolManager;

// DAO
public class TopCategoryDAO {
	
	PoolManager pool = PoolManager.getInstance();
	
	public List selectAll() {
		List list = new ArrayList();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from topcategory";
		
		con = pool.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TopCategory dto = new TopCategory();
				dto.setTopcategory_idx(rs.getInt("topcategory_idx"));
				dto.setTopname(rs.getString("topname"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt, rs);
		}
		return list;
	}
}
