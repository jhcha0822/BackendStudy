package com.sds.mall.domain;

import lombok.Data;

@Data
public class TopCategory {
	private int topcategory_idx;
	private String topname;
	
	public int getTopcategory_idx() {
		return topcategory_idx;
	}
	public void setTopcategory_idx(int topcategory_idx) {
		this.topcategory_idx = topcategory_idx;
	}
	public String getTopname() {
		return topname;
	}
	public void setTopname(String topname) {
		this.topname = topname;
	}
}
