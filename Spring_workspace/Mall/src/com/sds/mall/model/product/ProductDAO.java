package com.sds.mall.model.product;

import java.util.List;

import com.sds.mall.domain.Product;

public interface ProductDAO {
	public List selectAll(); 				// 모든 상품 가져오기 
	public Product select(int product_idx); // 상품 한 건 가져오기
	public List selectAllByTopIdx(int topcategory_idx);
	public List selectAllBySubIdx(int subcategory_idx); // 선택된 하위 카테고리에 소속된 상품 들 가져오기
														// select * from product where subcategory_idx=3;
	public void insert(Product product); // 상품 한 건 넣기
	public void update(Product product); // 상품 한 건 수정 
	public void delete(Product product); // 상품 한 건 삭제
}
