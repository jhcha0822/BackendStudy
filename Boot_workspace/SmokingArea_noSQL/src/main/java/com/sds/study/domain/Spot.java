package com.sds.study.domain;

import lombok.Data;

@Data
public class Spot {
	String id;			//계정식별 id
	String area_nm;		//장소명
	String area_desc;	//장소설명
	String ctprvnnm;	// 소재 시
	String signgunm;	// 소재 구
	String emdnm;		// 소재 동
	String area_se;		// 분류 (흡연, 비흡연)
	String area_ar;		// 관리번호
	String rdnmadr;		// 도로명주소
	String lnmadr;		// 지번주소 (구)
	String inst_nm;		// 관할기관
	String latitude;	// 위도
	String longitude;	// 경도
	String ref_date;	// 갱신일
	String fclty_knd;	// 이미지파일..?
	String note;		//메모	
	

}
