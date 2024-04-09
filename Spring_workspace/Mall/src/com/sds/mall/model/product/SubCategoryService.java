package com.sds.mall.model.product;

import java.util.List;

public interface SubCategoryService {
	public List selectAll();
	public List selectAllByTopIdx(int topcategory_idx);
}
