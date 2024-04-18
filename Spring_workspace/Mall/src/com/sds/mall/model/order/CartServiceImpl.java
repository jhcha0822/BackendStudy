package com.sds.mall.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.mall.domain.Cart;
import com.sds.mall.domain.Member;
import com.sds.mall.exception.CartException;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDAO cartDAO;
	
	@Override
	public void regist(Cart cart) {
		// TODO Auto-generated method stub
		Cart dto = cartDAO.selectDuplicate(cart); // 장바구니 내 동일한 상품이 있는지 조회
		if(dto == null) { // 동일한 상품이 없다면    
			cartDAO.insert(cart);						
		} else {
			cart.setEa(dto.getEa() + cart.getEa());
			cartDAO.update(cart); // 파라미터로 넘어온 개수 + 중복된 정보에 이미 들어있는 ea의 개수 합산하여 update
		}
		
	}

	@Override
	public List selectByMember(Member member) {
		// TODO Auto-generated method stub
		return cartDAO.selectByMember(member);
	}

	@Override
	public Cart select(int cart_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Cart cart) throws CartException {
		// TODO Auto-generated method stub
		cartDAO.update(cart);
	}
	
	@Override
	//@Transactional(propagation = Propagation.REQUIRED)
	public void updateGroup(List<Cart> cartList) throws CartException {
		// TODO Auto-generated method stub
		for(Cart cart : cartList) { // 장바구니의 수정할 목록 수 만큼 반복문으로 처리
			System.out.println("누가: "+cart.getMember().getMember_idx());
			System.out.println("무엇을: "+cart.getProduct().getProduct_idx());
			System.out.println("얼마나: "+cart.getEa());
			cartDAO.update(cart);
		}
	}

	@Override
	public void delete(Cart cart) throws CartException {
		// TODO Auto-generated method stub
		cartDAO.delete(cart);
	}

}
