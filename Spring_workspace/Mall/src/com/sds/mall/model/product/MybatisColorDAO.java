package com.sds.mall.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Color;
import com.sds.mall.exception.ColorException;

@Repository
public class MybatisColorDAO implements ColorDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(Color color) throws ColorException {
		// TODO Auto-generated method stub
		int result = sqlSessionTemplate.insert("Color.insert", color);
		
		if(result < 1)
			throw new ColorException("상품 색상 등록 실패");
	}

}
