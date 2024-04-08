package com.sds.mall.domain;

import lombok.Data;

@Data
public class SubCategory {
	
	private int subcategory_idx;
	private String subname;
	
	// fk 역할의 부모 객체
	private TopCategory topCategory;

	public int getSubcategory_idx() {
		return subcategory_idx;
	}

	public void setSubcategory_idx(int subcategory_idx) {
		this.subcategory_idx = subcategory_idx;
	}

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public TopCategory getTopCategory() {
		return topCategory;
	}

	public void setTopCategory(TopCategory topCategory) {
		this.topCategory = topCategory;
	}
}
