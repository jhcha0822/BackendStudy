package com.sds.springproject.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// Mybatis의 설정 xml 파일을 읽어 SqlSessionFactory 생성
// 특히 SqlSessionFactory는 오직 1개만 만들기 위해 아래 클래스는 SingleTon 패턴으로 정의

public class MybatisManager {
	
	// MybatisManager 객체의 인스턴스는 현재 클래스에서 제공 (생성자 호출 방지에 의해)
	private static MybatisManager instance;
	SqlSessionFactory factory;
	
	// 외부의 생성자 호출 방지
	private MybatisManager() {
		String resource = "com/sds/springproject/mybatis/mybatis-config.xml";	 // 확장자가 java가 아니면 디렉토리
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is !=null) {
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 외부에서 아래의 getter 호출을 통해 인스턴스 획득 가능
	// 인스턴스로 정의시 new가 필요하기에 static 메서드로 정의
	// static 영역은 같은 static 영역의 변수나 메서드만 접근 가능하기에 멤버변수도 static으로 선언
	public static MybatisManager getInstance() {
		if(instance == null) { //if 조건문은 객체가 null이 아닐때는 작동하지 않으므로, 오직 1회만 수행
			instance = new MybatisManager();
		}
		return instance;
	}
	
	// SqlSession을 얻는 메서드
	public SqlSession getSqlSession() {
		return factory.openSession();
	}
	
	// SqlSession 반납
	public void release(SqlSession sqlSession) {
		sqlSession.close();
	}
}
