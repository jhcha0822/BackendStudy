package db;

import java.sql.DriverManager; // JAVASE rt.jar�� ����
import java.sql.SQLException;
import java.sql.Connection;

public class OracleTest {
	public static void main(String[] args) {
		// Oracle�� �ƴ϶� ��� DBMS�� �����ϰ� �ʹٸ�
		// �ش� DBMS�翡�� �����ϴ� ����̹��� �̿��ؾ� �Ѵ�
		
		try {
		// Driver Load
		Class.forName("oracle.jdbc.driver.OracleDriver"); // Class�� ���� ������ ���� Ŭ������ Class Ŭ������ ��
		// �� Ŭ������ static, �� Ŭ�����޼��� �߿��� forName()�޼��带 �̿��ϸ�
		// Oracle������ ���õ� ����̺� Ŭ������ JVM�� �޸� ������ �ε� ����
		// �� ��Ű�� �� Ŭ������ JAVASE�� �⺻���� ž��� jar�� �ƴϱ� ���� (�ܺ� ���̺귯��)	
		System.out.println("����̹� �ε� ����");

		// Wendy/1234�� ����
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "wendy";
		String pw = "1234";
		
		Connection con = null; // ���� ���� �� ���� ������ ��ȯ�ϴ� ��ü
		// ���� �� ��ü�� null�̶�� ���ӿ� ������ ��. try���� �����ϴ��� �޸𸮿� con�� �־�� ��
		con = DriverManager.getConnection(url, id, pw); // ���� �õ� �� �����߻� ����: ����ó�� �߰�
		if(con != null)
			System.out.println("���ӿ� �����߽��ϴ�");
		} catch (ClassNotFoundException e) {
			// ���� �߻� �� JVM�� ���� ���� ��ü�� ClassNotFoundException�� �ν��Ͻ��� ����
			// catch���� �Ű����� e�� ����
			System.out.println("����̹��� Ȯ���� �ּ���");
		} catch (SQLException e) {
			System.out.println("���� ������ �߻��߽��ϴ�");
		}
	}
}
