package com.sds.mall.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Color;

@Repository
public class MybatisColorDAO implements ColorDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(Color color) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("Color.insert", color);
	}

}
