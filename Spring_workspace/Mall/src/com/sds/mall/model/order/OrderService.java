package com.sds.mall.model.order;

import java.util.List;

import com.sds.mall.domain.Member;
import com.sds.mall.domain.OrderSummary;

// DAO가 너무 많아 1대1로 서비스 생성하지 않고 약식으로 진행
// Order 관련한 DAO를 처리
public interface OrderService {
	
	// 받는 사람 가져오기
	public List selectReceiver(Member member); 
	
	// 결제 방법 가져오기
	public List selectPaymethod();
	
	// 주문 등록
	public void order(OrderSummary orderSummary);
	
}
