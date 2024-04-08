package com.sds.mall.model.product;

import java.util.List;

import com.sds.mall.domain.TopCategory;

// 중립
public interface TopCategoryService {
	public List selectAll();
	public TopCategory select(int topcategory_idx);
}
