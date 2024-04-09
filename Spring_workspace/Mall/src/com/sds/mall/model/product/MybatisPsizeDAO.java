package com.sds.mall.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Psize;

@Repository
public class MybatisPsizeDAO implements PsizeDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate; 
	
	@Override
	public void insert(Psize psize) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("Psize.insert", psize);
	}

}
