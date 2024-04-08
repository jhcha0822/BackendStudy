package com.sds.smokingproject.model.smokingarea;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sds.smokingproject.domain.SmokingArea;

// 서비스 인터페이스 구현체
@Service
public class SmokingAreaServiceImpl implements SmokingAreaService {

	@Autowired
	private SmokingAreaDAO smokingAreaDAO;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return smokingAreaDAO.selectAll();
	}

	@Override
	public SmokingArea select(int smokingArea_idx) {
		// TODO Auto-generated method stub
		return smokingAreaDAO.select(smokingArea_idx);
	}

}
