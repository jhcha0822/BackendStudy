package com.sds.mall.domain;

import lombok.Data;

@Data
public class OrderDetail {
	private int orderdetail_idx;
	private OrderSummary orderSummary; // 주문 정보
	private Product product;
	private int ea;
	
	// 차후 Product table의 수정에 영향받지 않기 위해
	private String product_name;
	private int price;
	
	public int getOrderdetail_idx() {
		return orderdetail_idx;
	}
	public void setOrderdetail_idx(int orderdetail_idx) {
		this.orderdetail_idx = orderdetail_idx;
	}
	public OrderSummary getOrderSummary() {
		return orderSummary;
	}
	public void setOrderSummary(OrderSummary orderSummary) {
		this.orderSummary = orderSummary;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
