package com.sds.asyncboard.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// 클라이언트의 요청이 들어오면 해당 클라이언트에게 Connection 객체를 제공해주기 위한
// 풀 관리자 객체를 정의하자. 사용완료된 Connection 객체는 다시 풀로 돌려보내진다

public class PoolManager {
	InitialContext context; // 검색 객체
	DataSource ds; // 커넥터 풀 구현체
	
	public PoolManager() {
		try {
			context =  new InitialContext(); // 검색 객체 생성
			// server.xml에 명시된 jndi 검색
			ds = (DataSource)context.lookup("java:comp/env/jndi/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}		
	}
	
	// 풀 내의 커넥션 하나 추출
	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	// 풀로 반환
	public void release(Connection con) {
		if(con != null) {
			try {
				con.close(); // 반납의 의미
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 오버로딩
	public void release(Connection con, PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con != null) {
			try {
				con.close(); // 반납의 의미
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 오버로딩
	public void release(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con != null) {
			try {
				con.close(); // 반납의 의미
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
