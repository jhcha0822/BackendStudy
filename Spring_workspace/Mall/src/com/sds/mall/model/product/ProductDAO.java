package com.sds.mall.model.product;

import java.util.List;

import com.sds.mall.domain.Product;

public interface ProductDAO {
	public List selectAll();
	public List selectAllBySubIdx(int subcategory_idx);
	public void insert(Product product);
	public void update(Product product);
	public void delete(Product product);
}
