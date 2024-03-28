package com.sds.asyncboard.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sds.asyncboard.common.PoolManager;

// CRUD를 위한 DAO
public class BoardDAO {
	
	// PoolManager pool = new PoolManager(); // Pool 관리 객체 생성
	PoolManager pool = PoolManager.getInstance(); // 싱글턴
	
	// insert
	public int insert(Board board) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = pool.getConnection();
		
		String sql = "insert into board(board_idx, title, writer, content)";
		sql += " values(seq_board.nextval, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt);
		}
				
		
		return result;
	}
	
	// select
	public List selectAll() {
		List list = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		con = pool.getConnection();
		
		String sql = "select * from board order by board_idx desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// List<Board>에 rs 값 저장
			while(rs.next()) {
				Board board = new Board();
				// board에 rs의 컬럼값 입력
				board.setBoard_idx(rs.getInt("board_idx"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt, rs);
		}
		
		return list;
	}
	
	// 글 한 건 가져오기
	public Board select(int board_idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		
		con = pool.getConnection();
		String sql = "select * from board where board_idx=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new Board();
				board.setBoard_idx(rs.getInt("board_idx"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt, rs);
		}
		
		return board;
	}
	
	// 글 한 건 수정하기
	public int update(Board board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		con = pool.getConnection();
		String sql = "update board set title=?, writer=?, content=? where board_idx=";
		sql += board.getBoard_idx();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt);
		}
		
		return result;
	}
}
