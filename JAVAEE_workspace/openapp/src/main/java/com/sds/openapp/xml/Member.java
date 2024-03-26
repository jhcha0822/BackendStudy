package com.sds.openapp.xml;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// 사원 한 명을 담기 위한 DTO

// DTO 생성 시 Lombok 라이브러리를 이용하면 일일이 getter/setter 를 만들지 않아도 된다
// `java -jar lombok.jar`
// pom.xml에 추가

// jdk1.5 부터 프로그래밍 언어 내에서 사용되는 주석을 지원
// 이러한 주석을 가리켜 어노테이션(Annotation) 이라 한다
// @로 표기

// 어노테이션 import 후 Window-Outline으로 확인 가능

@Data
public class Member {
	private int empno;
	private String ename;
	private int sal;
}
