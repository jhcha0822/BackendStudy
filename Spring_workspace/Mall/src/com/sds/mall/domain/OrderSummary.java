package com.sds.mall.domain;

import lombok.Data;

@Data
public class OrderSummary {
	private int orderSummary_idx;
	private Member member;
	private Paymethod paymethod;
	private Receiver receiver;
	private String orderdate;
	private int total_buy; // 구매 금액
	private int total_pay; // 실제 지불한 금액
	
	public int getOrdersummary_idx() {
		return orderSummary_idx;
	}
	public void setOrdersummary_idx(int orderSummary_idx) {
		this.orderSummary_idx = orderSummary_idx;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Paymethod getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(Paymethod paymethod) {
		this.paymethod = paymethod;
	}
	public Receiver getReceiver() {
		return receiver;
	}
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public int getTotal_buy() {
		return total_buy;
	}
	public void setTotal_buy(int total_buy) {
		this.total_buy = total_buy;
	}
	public int getTotal_pay() {
		return total_pay;
	}
	public void setTotal_pay(int total_pay) {
		this.total_pay = total_pay;
	}
}
