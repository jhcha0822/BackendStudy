package com.sds.dataroom.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sds.dataroom.common.PoolManager;

// DAO

public class DataroomDAO {
	PoolManager pool = PoolManager.getInstance(); // 싱글턴 객체 인스턴스
	
	// insert
	public int insert(Dataroom dataroom) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into dataroom(dataroom_idx, title, writer, content, filename)";
		sql += " values(seq_dataroom.nextval, ?, ?, ?, ?)";
		
		con = pool.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dataroom.getTitle());
			pstmt.setString(2, dataroom.getWriter());
			pstmt.setString(3, dataroom.getContent());
			pstmt.setString(4, dataroom.getFilename());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt);
		}
		
		return result;
	}
	
	// select all
	public List<Dataroom> selectAll() {
		List<Dataroom> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from dataroom order by dataroom_idx desc";
		
		con = pool.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// rs내 내용들을 꺼내 레코드 1건마다 DTO와 매핑
			rs.next();
			Dataroom dto = new Dataroom();
			while(rs.next()) {
				dto.setDataroom_idx(rs.getInt("dataroom_idx"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setHit(rs.getInt("hit"));
				dto.setFilename(rs.getString("filename"));
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
