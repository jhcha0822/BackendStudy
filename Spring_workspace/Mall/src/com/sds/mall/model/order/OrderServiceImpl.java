package com.sds.mall.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.mall.domain.Cart;
import com.sds.mall.domain.Member;
import com.sds.mall.domain.OrderDetail;
import com.sds.mall.domain.OrderSummary;
import com.sds.mall.exception.OrderException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ReceiverDAO receiverDAO;
	
	@Autowired
	private PaymethodDAO paymethodDAO;
	
	@Autowired
	private OrderSummaryDAO orderSummaryDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	@Override
	public List selectReceiver(Member member) {
		// TODO Auto-generated method stub
		return receiverDAO.selectAllByMember(member);
	}

	@Override
	public List selectPaymethod() {
		// TODO Auto-generated method stub
		return paymethodDAO.selectAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void order(OrderSummary orderSummary) throws OrderException {
		// TODO Auto-generated method stub
		// 주문 요약 입력
		orderSummaryDAO.insert(orderSummary);
		
		// 현재 회원의 장바구니 목록 획득
		List<Cart> cartList = cartDAO.selectByMember(orderSummary.getMember()); // paymentController에서 이미 세션을 통해 DB에 입력됨
		
		// 장바구니에 담긴 수만큼 반복하여 주문 상세 등록: transaction
		for(Cart cart : cartList) { // 위 리스트가 제너릭이어야 이 for문 사용 가능
			OrderDetail orderDetail = new OrderDetail();
			
			orderDetail.setOrderSummary(orderSummary);
			orderDetail.setProduct(cart.getProduct());
			orderDetail.setEa(cart.getEa());
			orderDetail.setProduct_name(cart.getProduct().getProduct_name());
			orderDetail.setPrice(cart.getProduct().getProduct_idx());
			
			orderDetailDAO.insert(orderDetail);
			
			// 회원이 사용하던 장바구니 지우기
			cartDAO.delete(cart);
		}
	}
	
}
