package com.sds.project0311.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Oracle이 내 PC에 설치되어 있건, 외부에 설치되어 있건, 네트워크로 접속을 시도해야 함
// SQLplus 대신 자바를 사용해 접속을 시도하는 것임. 과정이 크게 달라지지 않음
// 1) 오라클의 원격지 URL(ip, domain 등)
// 2) 계정 ID와 PW

public class OracleTest {
	public static void main(String[] args) {
		// 자바에서 오라클을 제어하기 위해서는 오라클용 클래스 집합인 드라이버.jar가 필요
		// jar: java archive, .class파일을 두새이상 모아놓은 압축파일을 뜻함
		// OracleDriver를 현재 클래스에 환경변수로 등록해야 하나, 
		// 이클립스를 사용할 경우 이 이클립스내에 등록하면 된다.
		
		// Driver Load

		Connection con = null;
		// 접속 성공 이후, 접속된 객체를 이용해 오라클에 insert문을 실행(DML)
		// jdbc에서 쿼리문 수행을 담당하는 객체: PreparedStatement (인터페이스)
		PreparedStatement pstmt = null; // 인터페이스는 개발자가 new 불가
		// jdbc에서 PreparedStatement 객체는 접속 객체인 Connection 객체로부터 인스턴스 획득 가능
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Dirver클래스명 명시
			System.out.println("드라이버 로드 성공");
			
			// 오라클에 접속
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String id = "WENDY";
			String pw = "1234"; 
			
			con = DriverManager.getConnection(url, id, pw);
			if(con != null)
				System.out.println("접속 성공");
			else
				System.out.println("접속 실패");
			
			String sql = "insert into car(car_idx, name, price) values(seq_car.nextval, 'BENZ', 8000)";
			pstmt = con.prepareStatement(sql); // SQL문을 네트워크를 통해 oracle에 전송
			// 오라클은 문장을 받아 쿼리문 수행: pstmt의 메서드를 호출할 때
			int result = pstmt.executeUpdate(); // insert, update, delete 메서드가 따로 있는 것이 아니라
			// 모든 DML 쿼리는 executeUpdate 메서드로 수행됨	
			if(result<1) // 0은 실패로 간주
				System.out.println("등록 실패");
			else // insert 성공 시 한개의 레코드가 영향을 받기에 1이 반환됨
				System.out.println("등록 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로드 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			// try 문을 거친 실행부이건, catch문을 거쳐온 실행부이건, 이 finally 블럭에 걸리게 됨
			// 다 사용된 데이터베이스 접속 및 쿼리문실행 객체는 모두 닫기
			// 다중사용자일경우 메모리 낭비 및 서버 다운의 원인이 됨
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
	}
}
