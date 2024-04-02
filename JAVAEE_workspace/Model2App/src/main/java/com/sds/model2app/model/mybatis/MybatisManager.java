package com.sds.model2app.model.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// SQL Session들이 모여서 관리되는 SqlSessionFactory는 하나의 어플리케이션 내에서 여러개 만들 필요가 없음
// 아래의 클래스를 Singleton으로 정의하고, 멤버변수로 SqlSessionFactory를 선언하면 오직 1개의 객체만 관리 가능하다
// Singleton을 적용한 poolManager와 같은 기능

public class MybatisManager {
	private static MybatisManager instance;
	SqlSessionFactory factory;
	
	// 외부에서 new 연산자로 인스턴스 생성을 금지
	private MybatisManager() {
		String resource = "com/sds/model2app/mybatis/mybatis-config.xml";
		
		try {
			
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
			inputStream.close(); //스트림 닫기
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 이 클래스가 아니면 외부에서 인스턴스를 얻어갈 수 없게끔
	public static MybatisManager getInstance() {
		if(instance == null) { // null인 경우만 인스턴스 생성. 오직 1회 동작
			instance = new MybatisManager();
		}
		return instance;
	}
	
	// factory로부터 SqlSession 하나를 반환
	public SqlSession getSqlSession() {
		return factory.openSession();
	}
	
	// SqlSession 사용후 반납
	public void release(SqlSession sqlSession) {
		sqlSession.close();
	}
}
