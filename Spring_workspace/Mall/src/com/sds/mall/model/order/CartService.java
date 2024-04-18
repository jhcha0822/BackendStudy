package com.sds.mall.model.order;

import java.util.List;

import com.sds.mall.domain.Cart;
import com.sds.mall.domain.Member;

public interface CartService {
	
	public void regist(Cart cart); 			   		// 장바구니 등록. insert보다 상위 개념
	public List selectByMember(Member member); 		// 장바구니 목록
	public Cart select(int cart_idx);		   		// 상품 1건 정보
	public void update(Cart cart); 			   		// 장바구니 수정
	public void updateGroup(List<Cart> cartList);   // 장바구니 내 여러건 수정
	public void delete(Cart cart); 			   		// 장바구니 삭제
	
}