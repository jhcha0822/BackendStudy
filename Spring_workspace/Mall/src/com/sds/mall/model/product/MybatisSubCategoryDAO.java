package com.sds.mall.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisSubCategoryDAO implements SubCategoryDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAll() {
		
		return null;
	}

	public List selectAllByTopIdx(int topcategory_idx) {
		
		return sqlSessionTemplate.selectList("SubCategory.selectAllByTopIdx", topcategory_idx);
	}

}
