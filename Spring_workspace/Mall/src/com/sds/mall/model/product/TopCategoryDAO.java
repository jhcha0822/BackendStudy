package com.sds.mall.model.product;

import java.util.List;

import com.sds.mall.domain.TopCategory;

// TopCategort 테이블에 대한 CRUD를 담당하는 DAO 객체 중 최상위 객체
public interface TopCategoryDAO {

	public List selectAll();
	public TopCategory select(int topcategory_idx);
	
}
