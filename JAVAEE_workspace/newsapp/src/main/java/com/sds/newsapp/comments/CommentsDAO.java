package com.sds.newsapp.comments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DAO for CRUD
public class CommentsDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "seshop";
	String pw = "1234";
	
	// 특정 뉴스기사에 댓글 추가하기
	public int insert(Comments comments) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			Class.forName(driver); // 드라이버 로드
			con = DriverManager.getConnection(url, user, pw);
			
			String sql = "insert into comments(comments_idx, msg, cwriter, news_idx)";
			sql += " values(seq_comments.nextval, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql); // 쿼리 객체 생성
			
			pstmt.setString(1, comments.getMsg());
			pstmt.setString(2, comments.getCwriter());
			pstmt.setInt(3, comments.getNews_idx());
			
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
	
	// 특정 뉴스기사의 댓글 목록 가져오기
	public List selectAllByFkey(int news_idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List list = new ArrayList(); // Comments DTO 의 인스턴스를 보관할 컬렉션
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pw);
			String sql = "select * from comments where news_idx=? order by comments_idx desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, news_idx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// rs에 들어있는 컬럼의 값들을 DTO의 멤버변수로 이동
				Comments comments = new Comments();
				comments.setComments_idx(rs.getInt("comments_idx"));
				comments.setMsg(rs.getString("msg"));
				comments.setCwriter(rs.getString("cwriter"));
				comments.setCregdate(rs.getString("cregdate"));
				comments.setNews_idx(rs.getInt("news_idx"));
				
				list.add(comments);
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
