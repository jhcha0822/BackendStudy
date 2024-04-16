package com.sds.mall.domain;

import java.util.List;

import lombok.Data;

@Data
public class TopCategory {
	private int topcategory_idx;
	private String topname;
	
	// 하나의 TopCategory가 여러개의 하위 카테고리 보유
	private List<SubCategory> subList;
	
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
	public List<SubCategory> getSubList() {
		return subList;
	}
	public void setSubList(List<SubCategory> subList) {
		this.subList = subList;
	}
}
