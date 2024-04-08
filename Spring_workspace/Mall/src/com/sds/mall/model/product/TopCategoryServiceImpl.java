package com.sds.mall.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.mall.domain.TopCategory;

// 서비스 객체의 자동 인스턴스 생성을 Spring component-scan에게 부탁
@Service
public class TopCategoryServiceImpl implements TopCategoryService {

	// DAO에게 업무를 전달하기에 보유(느슨하게)
	@Autowired
	private TopCategoryDAO topCategoryDAO;
	
	@Override
	public List selectAll() {
		return topCategoryDAO.selectAll();
	}

	@Override
	public TopCategory select(int topcategory_idx) {
		return null;
	}

}
