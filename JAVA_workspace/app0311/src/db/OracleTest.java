package db;

import java.sql.DriverManager; // JAVASE rt.jar에 있음
import java.sql.SQLException;
import java.sql.Connection;

public class OracleTest {
	public static void main(String[] args) {
		// Oracle뿐 아니라 모든 DBMS를 연동하고 싶다면
		// 해당 DBMS사에서 제공하는 드라이버를 이용해야 한다
		
		try {
		// Driver Load
		Class.forName("oracle.jdbc.driver.OracleDriver"); // Class에 대한 정보를 가진 클래스는 Class 클래스라 함
		// 이 클래스의 static, 즉 클래스메서드 중에서 forName()메서드를 이용하면
		// Oracle연동과 관련된 드라이브 클래스를 JVM의 메모리 영역에 로드 가능
		// 위 패키지 및 클래스는 JAVASE에 기본으로 탑재된 jar가 아니기 때문 (외부 라이브러리)	
		System.out.println("드라이버 로드 성공");

		// Wendy/1234로 접속
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "wendy";
		String pw = "1234";
		
		Connection con = null; // 접속 성공 시 접속 정보를 반환하는 객체
		// 따라서 이 객체가 null이라면 접속에 실패한 것. try문이 성공하더라도 메모리에 con이 있어야 함
		con = DriverManager.getConnection(url, id, pw); // 접속 시도 시 에러발생 가능: 예외처리 추가
		if(con != null)
			System.out.println("접속에 성공했습니다");
		} catch (ClassNotFoundException e) {
			// 에러 발생 시 JVM에 의해 예외 객체인 ClassNotFoundException의 인스턴스가 생성
			// catch문의 매개변수 e로 전달
			System.out.println("드라이버를 확인해 주세요");
		} catch (SQLException e) {
			System.out.println("접속 문제가 발생했습니다");
		}
	}
}
