package com.sds.mall.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	public SubCategoryDAO subCategoryDAO;
	
	public List selectAll() {
		return null;
	}

	
	public List selectAllByTopIdx(int topcategory_idx) {
		return subCategoryDAO.selectAllByTopIdx(topcategory_idx);
	}


}
