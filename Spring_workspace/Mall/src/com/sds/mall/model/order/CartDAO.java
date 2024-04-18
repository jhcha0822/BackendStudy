package com.sds.mall.model.order;

import java.util.List;

import com.sds.mall.domain.Cart;
import com.sds.mall.domain.Member;

public interface CartDAO {
	public Cart selectDuplicate(Cart cart);    // 장바구니 내 중복된 상품이 등록되었는지 조회
	public void insert(Cart cart); 			   // 장바구니 등록
	public List selectByMember(Member member); // 장바구니 목록
	public Cart select(int cart_idx);		   // 상품 1건 정보
	public void update(Cart cart); 			   // 장바구니 수정
	public void delete(Cart cart); 			   // 장바구니 삭제	
}
