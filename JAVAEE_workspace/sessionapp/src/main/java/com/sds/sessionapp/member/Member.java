package com.sds.sessionapp.member;

//세션 객체에 회원 정보를 낱개로 파편화하여 담지 않고,
// 객체지향적으로 처리: 클래스의 인스턴스를 담아보기
// 클래스 중, 로직이 아닌 데이터만을 담기위한 클래스를 가리켜 DTO라고 함
// Data Transfer Object

// DTO 인스턴스 한개에는 한 사람에 대한 정보를 담는다.

// 데이터베이스의 테이블명과 일치
public class Member {
	private String id;
	private String pass;
	private String name;
	private String email;
	private int receive;
	private String regdate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getReceive() {
		return receive;
	}
	public void setReceive(int receive) {
		this.receive = receive;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
