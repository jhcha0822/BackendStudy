package com.sds.mall.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.TopCategory;

// Spring의 component:scan에게 이 DAO의 인스턴스 생성을 요청
@Repository
public class MybatisTopCategoryDAO implements TopCategoryDAO {

	// 주입
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("TopCategory.selectAll");
	}

	@Override
	public TopCategory select(int topcategory_idx) {
		return null;
	}

}
