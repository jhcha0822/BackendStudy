package com.sds.movieapp.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

// OpenAPI에서 받아온 영화 1건을 담는 DTO
@Data
public class Movie {
	
	private int movie_idx;

	// 웹상의 이미지 경로
	private String url;
	
	////////////////////////////////////////////////////////////////////
	
	// API에 요청시 사용할 파라미터
	private String curPage = "";					//현재페이지
	private String itemPerPage = "100";				//결과row수
//	private String movieNm = "";					//영화명
	private String directorNm = "";					//감독명
	private String openStartDt = "";				//개봉연도 시작조건 ( YYYY )
	private String openEndDt = "";					//개봉연도 끝조건 ( YYYY )	
	private String prdtStartYear = "";				//제작연도 시작조건 ( YYYY )
	private String prdtEndYear = "";				//제작연도 끝조건    ( YYYY )
	private String repNationCd = "";				//대표국적코드 (공통코드서비스에서 '2204'로 조회된 국가코드)
	private String[] movieTypeCdArr = null;			//영화형태코드 배열 (공통코드서비스에서 '2201'로 조회된 영화형태코드)
	
	////////////////////////////////////////////////////////////////////
	
	// 1편의 영화를 불러왔을 때 사용할 변수
	private String movieCd;
	private String movieNm;
	private String movieNmEn;
	private String prdtYear;
	private String openDt;
	private String typeNm;
	private String prdtStatNm;
	private String nationAlt;
	private String genreAlt;
	private String repNationNm;
	private String repGenreNm;
	
	// JSON 내 또 다른 객체배열 존재 -> DTO 선언하여 포함
	private List<Director> directors;
	private List<Company> companys;	
	
	////////////////////////////////////////////////////////////////////
	
	MultipartFile file; // html 컴포넌트와 일치되어야 자동 매핑: bean이 자동으로 처리
	
}
