package com.sds.movieadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.soap.movie.MovieAPIServiceImplService;
import kr.or.kobis.kobisopenapi.consumer.soap.movie.MovieInfoResult;
import kr.or.kobis.kobisopenapi.consumer.soap.movie.OpenAPIFault;

@RestController
public class SampleController {
	
	// 발급키
	// Spring-context에서 관리
	@Autowired
	private String key;
	
	// KOBIS 오픈 API Rest Client를 통해 호출
	KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
	
	// KOBIS 오픈 API Rest Client를 통해 코드 서비스 호출 (boolean isJson, String comCode )
	ObjectMapper mapper = new ObjectMapper();
	
	// 영화 목록을 가져오는 메서드
	@GetMapping(value="/movie/movieList", produces="application/json;charset=UTF-8")
	public String sample1() {
		
		// 파라메터 설정
		String curPage = "";					//현재페이지
		String itemPerPage = "";		//결과row수
		String movieNm = "";						//영화명
		String directorNm = "";				//감독명
		String openStartDt = "";			//개봉연도 시작조건 ( YYYY )
		String openEndDt = "";				//개봉연도 끝조건 ( YYYY )	
		String prdtStartYear = "";	//제작연도 시작조건 ( YYYY )
		String prdtEndYear = "";			//제작연도 끝조건    ( YYYY )
		String repNationCd = "";			//대표국적코드 (공통코드서비스에서 '2204'로 조회된 국가코드)
		String[] movieTypeCdArr = null;	//영화형태코드 배열 (공통코드서비스에서 '2201'로 조회된 영화형태코드)
		
		String movieCdResponse = null;
		try {
			// 영화코드조회 서비스 호출 (boolean isJson, String curPage, String itemPerPage,String directorNm, String movieCd, String movieNm, String openStartDt,String openEndDt, String ordering, String prdtEndYear, String prdtStartYear, String repNationCd, String[] movieTypeCdArr)
			movieCdResponse = service.getMovieList(true, curPage, itemPerPage, movieNm, directorNm, openStartDt, openEndDt, prdtStartYear, prdtEndYear, repNationCd, movieTypeCdArr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// request.setAttribute("nationCd",nationCd);
		
		return movieCdResponse;
	}
	
	// 국가 정보 조회
	@GetMapping(value="/movie/nation", produces="application/json;charset=UTF-8")
	public String sample2() {
		String nationCdResponse = null;
	
		try {
			nationCdResponse = service.getComCodeList(true,"2204");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nationCdResponse;
	}
	
	// 영화 타입 조회
	@GetMapping(value="/movie/type", produces="application/json;charset=UTF-8")
	public String sample3() {
		String movieTypeCdResponse = null;
		
		try {
			movieTypeCdResponse = service.getComCodeList(true,"2201");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movieTypeCdResponse;
	}
	
	// 영화 1편 조회 : /movie/222013
	@GetMapping(value="/movie/{movieCd}", produces="application/json;charset=UTF-8")
	public MovieInfoResult sample3(@PathVariable("movieCd") String movieCd) { // moviecd는 경로가 아닌 변수임을 명시
		MovieInfoResult movieInfoResult = null;
		
		try {
			movieInfoResult = new MovieAPIServiceImplService().getMovieAPIServiceImplPort().searchMovieInfo(key, movieCd);
		} catch (OpenAPIFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return movieInfoResult;
	}
	
}
