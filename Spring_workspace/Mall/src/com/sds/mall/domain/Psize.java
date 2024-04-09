package com.sds.mall.domain;

import lombok.Data;

@Data
public class Psize {
	private int psize_idx;
	private String size_name;
	
	 // 부모 참조
	private Product product;

	public int getPsize_idx() {
		return psize_idx;
	}

	public void setPsize_idx(int psize_idx) {
		this.psize_idx = psize_idx;
	}

	public String getSize_name() {
		return size_name;
	}

	public void setSize_name(String size_name) {
		this.size_name = size_name;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
