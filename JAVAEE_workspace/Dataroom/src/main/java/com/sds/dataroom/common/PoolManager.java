package com.sds.dataroom.common;

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
	
	// 생성자가 접근이 제한되었기에 PoolManager의 인스턴스를 제공해야 할 의무를 클래스가 부담
	private static PoolManager instance; // static 메서드에서 접근 가능하게 static 설정
	
//	public PoolManager() {
//		try {
//			context =  new InitialContext(); // 검색 객체 생성
//			// server.xml에 명시된 jndi 검색
//			ds = (DataSource)context.lookup("java:comp/env/jndi/oracle");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}		
//	}
	
	// singleton으로 정의하기 위해 접근제한자 설정
	private PoolManager() {
		try {
			context =  new InitialContext(); // 검색 객체 생성
			// server.xml에 명시된 jndi 검색
			ds = (DataSource)context.lookup("java:comp/env/jndi/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}		
	}
	
	// 인스턴스 변수인 instance에 대해 직접 접근이 불가하기에, getter를 통해 인스턴스를 반환하도록 메서드 정의
	public static PoolManager getInstance() { // new 없이 사용가능하게 static 설정
		if(instance == null)
			instance = new PoolManager();
		return instance;
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
