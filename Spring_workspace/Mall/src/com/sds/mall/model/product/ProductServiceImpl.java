package com.sds.mall.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.mall.domain.Product;
import com.sds.mall.model.common.FileManager;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ColorDAO colorDAO;
	
	@Autowired
	private PsizeDAO psizeDAO;
	
	@Override
	public void regist(Product product) {
		// TODO Auto-generated method stub
		
		// upload
		fileManager.save(product);
		
		// DB insert
		productDAO.insert(product); // product insert 후 product dto에 pk가 채워짐
									// product dto 안에는 colorList도 보유
		for(Color : product.getColorList()) { // color; user가 선택한 색상 수만큼 반복하여 진행
			
		}
		// psize
		
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAllBySubIdx(int subcategory_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product select(int product_idx) {
		// TODO Auto-generated method stub
		return null;
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
