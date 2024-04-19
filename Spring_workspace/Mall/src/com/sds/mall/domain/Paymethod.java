package com.sds.mall.domain;

import lombok.Data;

@Data
public class Paymethod {
	private int paymethod_idx;
	private String payname;
	
	public int getPaymethod_idx() {
		return paymethod_idx;
	}
	public void setPaymethod_idx(int paymethod_idx) {
		this.paymethod_idx = paymethod_idx;
	}
	public String getPayname() {
		return payname;
	}
	public void setPayname(String payname) {
		this.payname = payname;
	}
}
