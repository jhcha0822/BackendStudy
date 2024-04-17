package com.sds.mall.domain;

import lombok.Data;

@Data
public class MemberDetail {
	private int member_detail_idx;
	private String password;
	private String phone;
	private String addr;
	
	private Member member;

	public int getMember_detail_idx() {
		return member_detail_idx;
	}

	public void setMember_detail_idx(int member_detail_idx) {
		this.member_detail_idx = member_detail_idx;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
