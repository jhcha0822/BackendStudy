package com.sds.smokingproject.model.smokingarea;

import java.util.List;

import com.sds.smokingproject.domain.SmokingArea;

public interface SmokingAreaDAO {
	public List selectAll(); 				  //모든 레코드 가져오기 
	public SmokingArea select(int smokingArea_idx); //데이터 한건 가져오기
}