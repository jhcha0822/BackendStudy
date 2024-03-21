package com.sds.newsapp.news;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 이 클래스는 javaEE와 javaSE 모두
// DB의 특정 테이블에 대해 CRUD가 가능하게 지원 (중립적 객체)
// 플랫폼에 독립적인 재사용 객체로 정의하기 위함
// DAO(Data Access Object)

public class NewsDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "seshop";
	String pw = "1234";
	
	// CREATE(INSERT)
	public int insert(News news) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;	
		
		try {
			Class.forName(driver);
			// System.out.println("드라이버 로드");
			con = DriverManager.getConnection(url, user, pw);	
			StringBuffer sb = new StringBuffer();	
			if(con != null) {
				// 오라클에서 바인드 변수를 사용하기 위해서 :변수명 을 사용하지만,
				// jdbc 코드에서는 ? 로 처리
				// 바인드 변수의 목적은 db의 성능 개선 및 향상
				sb.append("insert into news(news_idx, title, writer, content)");
				sb.append(" values(seq_news.nextval, ?, ?, ?)");
				
				pstmt = con.prepareStatement(sb.toString());
				pstmt.setString(1, news.getTitle());
				pstmt.setString(2, news.getWriter());
				pstmt.setString(3, news.getContent());
				
				result = pstmt.executeUpdate(); // 쿼리 실행
			}
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
	
	// READ(SELECT)
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<News> list = new ArrayList<News>(); // DTO를 담을 list
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pw);
			String sql = "select * from news order by news_idx desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// rs를 닫게 되면, rs를 받아간 객체에서 사용할 수 없다
			// rs를 대신할 수 있는 자바의 자료형 생성이 필요하다
			while(rs.next()){
				// 한건의 레코드를 담게 될 빈 DTO 생성
				News news = new News();
				news.setNews_idx(rs.getInt("news_idx"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
				list.add(news);
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
	
	// 게시물 한 건 가져오기
	public News select(int news_idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		News news = new News(); // DTO
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pw);
			String sql = "select * from news where news_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, news_idx);
			rs = pstmt.executeQuery();
			// 게시물 1건의 저오가 담긴 rs가 닫히게 되면 외부에서 rs을 사용 불가능
			// rs에 들어있는 레코드 한 건을 DTO에 담아 반환하자
			if(rs.next()) {
				news.setNews_idx(rs.getInt("news_idx"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
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
		return news;
	}
	
	// UPDATE
	public int update(News news) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pw);
			String sql = "update news set title=?, writer=?, content=? where news_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, news.getTitle());
			pstmt.setString(2, news.getWriter());
			pstmt.setString(3, news.getContent());
			pstmt.setInt(4, news.getNews_idx());
			
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
	
	// DELETE
	public int delete(int news_idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pw);
			String sql = "delete news where news_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, news_idx);
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
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
}
