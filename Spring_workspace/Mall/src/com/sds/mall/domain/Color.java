package com.sds.mall.domain;

import lombok.Data;

@Data
public class Color {
	private int color_idx;
    private String color_name;
    
    // 부모 참조
    private Product product;

	public int getColor_idx() {
		return color_idx;
	}

	public void setColor_idx(int color_idx) {
		this.color_idx = color_idx;
	}

	public String getColor_name() {
		return color_name;
	}

	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
