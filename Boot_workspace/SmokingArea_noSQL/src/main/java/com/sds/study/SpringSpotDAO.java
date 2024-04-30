package com.sds.study;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.study.domain.Spot;

@Repository
public class SpringSpotDAO implements SpotDAO{
	
	@Autowired
	private ApiManager apiManager;

	@Override
	public List Load() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spot select(Spot spot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spot selectByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Spot spot) {
		// TODO Auto-generated method stub
		
	}

	
}
