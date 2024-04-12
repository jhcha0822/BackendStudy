package com.sds.mall.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Psize;
import com.sds.mall.exception.PsizeException;

@Repository
public class MybatisPsizeDAO implements PsizeDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate; 
	
	@Override
	public void insert(Psize psize) throws PsizeException {
		// TODO Auto-generated method stub
		int result = sqlSessionTemplate.insert("Psize.insert", psize);
		
		// result = 0; // 예외 테스트
		
		if(result < 1)
			throw new PsizeException("상품 사이즈 등록 실패");
		
	}

}
