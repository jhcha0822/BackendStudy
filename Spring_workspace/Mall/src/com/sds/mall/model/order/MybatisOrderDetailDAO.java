package com.sds.mall.model.order;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.OrderDetail;
import com.sds.mall.exception.OrderException;

@Repository
public class MybatisOrderDetailDAO implements OrderDetailDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(OrderDetail orderDetail) throws OrderException {
		// TODO Auto-generated method stub
		int result = sqlSessionTemplate.insert("OrderDetail.insert", orderDetail);
		if(result < 1) {
			throw new OrderException("주문 상세 정보 등록 실패");
		}
	}
}