package com.sds.mall.domain;

import lombok.Data;

@Data
public class SnS {
	private int sns_idx;
	private String sns_name;
	
	public int getSns_idx() {
		return sns_idx;
	}
	public void setSns_idx(int sns_idx) {
		this.sns_idx = sns_idx;
	}
	public String getSns_name() {
		return sns_name;
	}
	public void setSns_name(String sns_name) {
		this.sns_name = sns_name;
	}
}
