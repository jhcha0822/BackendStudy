package com.sds.study;

import java.util.List;

import com.sds.study.domain.Spot;

public interface SpotDAO {
	public List Load();
	public Spot select(Spot spot);
	public Spot selectByID(String id);
	public void update(Spot spot);
		
}
