package com.sds.mall.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Product;
import com.sds.mall.exception.ProductException;

@Repository
public class MybatisProductDAO implements ProductDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("Product.selectAll");
	}

	@Override
	public List selectAllBySubIdx(int subcategory_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Product product) throws ProductException{
		// TODO Auto-generated method stub
		int result = sqlSessionTemplate.insert("Product.insert", product);
		
		if(result < 1)
			throw new ProductException("상품 등록 실패");
		
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub
		
	}

}
