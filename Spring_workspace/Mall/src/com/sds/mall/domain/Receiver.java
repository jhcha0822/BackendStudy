package com.sds.mall.domain;

import lombok.Data;

@Data
public class Receiver {
	private int receiver_idx;
	private Member member;
	private String addr;
	
	public int getReceiver_idx() {
		return receiver_idx;
	}
	public void setReceiver_idx(int receiver_idx) {
		this.receiver_idx = receiver_idx;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}
