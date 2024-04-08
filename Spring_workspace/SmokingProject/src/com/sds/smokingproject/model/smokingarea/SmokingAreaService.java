package com.sds.smokingproject.model.smokingarea;

import java.util.List;

import com.sds.smokingproject.domain.SmokingArea;

/*
 * 하위 컨트롤러가, 서비스를 이용할때 서로 간 결합도가 낮아야 하므로  DI구현을 위해 서비스 객체 조차 
 * 인터페이스인 최상의 객체를 정의해 놓는다
 * */

public interface SmokingAreaService {
	public List selectAll(); 				  //모든 레코드 가져오기 
	public SmokingArea select(int smokingArea_idx); //데이터 한건 가져오기
}
