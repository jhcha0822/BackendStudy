package com.sds.mall.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.mall.domain.Color;
import com.sds.mall.domain.Product;
import com.sds.mall.domain.Psize;
import com.sds.mall.exception.ColorException;
import com.sds.mall.exception.ProductException;
import com.sds.mall.exception.PsizeException;
import com.sds.mall.exception.UploadException;
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
	
	// 등록 업무는 4가지 세부 업무를 완료해야 전체를 성공으로 봄
	// 전파규칙은 디폴트 값, 기존 트랜젝션이 있다면 그 트랜젝션을 이용한다는 옵션
	// 아래의 서비스 메서드에서 동작중인 DAO의 메서드들 중
	// 단 하나라도 RuntimeException이 발생할 경우 아래 메서드 영역내에서 발생한 모든 DML Rollback 
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Product product) throws ProductException, ColorException, PsizeException { // throws UploadException { // 한 단 위쪽의 Controller에게까지 예외 전달
		// TODO Auto-generated method stub
		
		// upload
		fileManager.save(product); // UploadException은 RuntimeException이기에 컴파일러가 관여 X
		
		// DB insert
		productDAO.insert(product); // product insert 후 product dto에 pk가 채워짐
									// product dto 안에는 colorList도 보유
		for(Color color : product.getColorList()) { // color; user가 선택한 색상 수만큼 반복하여 진행
			// 색상과 사이즈 입력시 방금 위에서 입력한 product의 pk 이용
			color.setProduct(product); // DTO 형태로 입력
			colorDAO.insert(color); // Color DAO에 insert 요청
		}

		for(Psize psize : product.getPsizeList()) {
			psize.setProduct(product);
			psizeDAO.insert(psize);
		}
		
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return productDAO.selectAll();
	}

	@Override
	public List selectAllByTopIdx(int topcategory_idx) {
		// TODO Auto-generated method stub
		return productDAO.selectAllByTopIdx(topcategory_idx);
	}
	
	@Override
	public List selectAllBySubIdx(int subcategory_idx) {
		// TODO Auto-generated method stub
		return productDAO.selectAllBySubIdx(subcategory_idx);
	}

	@Override
	public Product select(int product_idx) {
		// TODO Auto-generated method stub
		return productDAO.select(product_idx);
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
