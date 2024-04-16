package com.sds.mall.model.product;

import java.util.List;

import com.sds.mall.domain.Product;

public interface ProductService {
	public void regist(Product product); 				// 파일을 포함하여 상품 등록(DB insert + 업로드)
	public List selectAll(); 							// 모든 상품 조회
	public List selectAllByTopIdx(int topcategory_idx); // 상위 카테고리에 소속된 상품 목록 조회
	public List selectAllBySubIdx(int subcategory_idx); // 하위 카테고리에 소속된 상품 목록 조회
	public Product select(int product_idx); 			// 상품 한 건 조회
	public void update(Product product); 				// 한 건 수정
	public void delete(Product product);				// 한 건 삭제
}
