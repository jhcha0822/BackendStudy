package com.sds.springmvc.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sds.springmvc.domain.Board;

// mybatis는 SE와 EE 모두 사용이 가능한 중립적 퍼시스턴스(Persistance: DB영역) 프레임워크
// web인 model2 프로젝트에서 사용하기 전 SE에서 동작 여부를 확인해보자

// mybatis 설치가 필요: jar 보유 (pom.xml에서 보유함)

// mybatis: 개발자가 java 코드 내에 쿼리문을 작성하지 않고, xml에서 SQL문을 작성할 수 있도록 지원
// 			jdbc 코드를 직접 핸들링하지 않고 내부적으로 처리
//			개발 시 쿼리문에 집중이 가능하여 jdbc 개발 시 작성했던 상투적 코드(Connection, PreparedStatement)등을 작성할 필요가 사라짐

public class Test {
	
	public static void main(String[] args) {
		// mybatis 관련 설정파일을 읽어 자바코드에서 mybatis를 사용하는 코드
		
		try {
			String resource = "com/sds/model2app/mybatis/mybatis-config.xml";
			// mybatis 설정 xml에 접근해 스트림 생성
			InputStream inputStream = Resources.getResourceAsStream(resource);
			
			// 스트림을 이용해 쿼리문 수행 객체인 SqlSession 객체를 얻기 위해 SqlSessionFactory 얻기
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
			
			// 쿼리 수행 객체 얻기
			SqlSession sqlSession = factory.openSession();
			inputStream.close();
			
			// DTO에 정보를 넣어 메서드 매개변수로 전달
			Board board = new Board();
			board.setTitle("mybatis로 넣은 제목");
			board.setWriter("oo");
			board.setContent("Persistance 영역의 Framework인 Mybatis 실습");
			
			// 쿼리문 호출
			// mybatis-config 내 mapper가 많아 이름이 중복될 수 있음
			// namespace를 통해 접근 -> 유일성
			// mybatis의 SQLSession은 내부적으로 jdbc를 이용하여 쿼리문을 실행
			// 디폴트 트랜젝션 설정 autoCommit = false (setAutoCommit(false)와 동일)
			int result = sqlSession.insert("com.sds.model2app.domain.Board.insert", board);
			sqlSession.commit(); // commit
			System.out.println(result);
			sqlSession.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
