package com.sds.mall.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Product {
	private int product_idx;
	private String product_name;
	private String brand;
	private int price;
	private String filename; // 바이너리 파일
	
	private MultipartFile photo; // html 에서의 <input type="file"> 컴포넌트와 이름이 일치할경우 이 객체로 바이너리를 포함한 정보: 업로드 정보가 들어온다
	
	private String detail;
	
	// 하나의 상품은 여러개의 Color를 자식으로 보유할 수 있다
	List<Color> colorList;
	List<Psize> psizeList;

	// 부모 참조
	private SubCategory subCategory;

	public int getProduct_idx() {
		return product_idx;
	}

	public void setProduct_idx(int product_idx) {
		this.product_idx = product_idx;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public List<Color> getColorList() {
		return colorList;
	}

	public void setColorList(List<Color> colorList) {
		this.colorList = colorList;
	}

	public List<Psize> getPsizeList() {
		return psizeList;
	}

	public void setPsizeList(List<Psize> psizeList) {
		this.psizeList = psizeList;
	}

}
