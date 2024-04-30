package com.sds.movieadmin.model.movie;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sds.movieadmin.domain.Director;
import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.domain.MovieType;
import com.sds.movieadmin.domain.Nation;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.soap.movie.MovieAPIServiceImplService;
import kr.or.kobis.kobisopenapi.consumer.soap.movie.MovieInfoResult;
import kr.or.kobis.kobisopenapi.consumer.soap.movie.OpenAPIFault;

// API를 호출하여 각종 정보를 가져오는 전담 서비스
// 컨트롤러가 길어져 따로 서비스로 적용
@Service
public class MovieApiService {
	
	@Autowired
	private String key;
	
	@Autowired
	private KobisOpenAPIRestService kobisOpenAPIRestService;
	
	// 영화 목록 조회
	public List getMovieList(Movie movie) {
		String movieCdResponse = null;
		List<Movie> movieList = null;		
 		try {
			// 영화코드조회 서비스 호출 (boolean isJson, String curPage, String itemPerPage,String directorNm, String movieCd, String movieNm, String openStartDt,String openEndDt, String ordering, String prdtEndYear, String prdtStartYear, String repNationCd, String[] movieTypeCdArr)
			movieCdResponse = kobisOpenAPIRestService.getMovieList(true, movie.getCurPage(), movie.getItemPerPage(), movie.getMovieNm(), movie.getDirectorNm(), movie.getOpenStartDt(), movie.getOpenEndDt(), movie.getPrdtStartYear(), movie.getPrdtEndYear(), movie.getRepNationCd(), movie.getMovieTypeCdArr());

			System.out.println(movieCdResponse);
			
			// JSON 문자열을 java 객체로 변환(json simple)
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject)parser.parse(movieCdResponse); // 가장 바깥쪽 JSON 반환
			json = (JSONObject)json.get("movieListResult"); // 내부의 json 반환
			JSONArray array = (JSONArray)json.get("movieList"); // 영화 배열 반환
			
			// JSON simple은 파싱에는 강하나
			// 파싱 결과를 List 등 컬렉션으로 바꾸기 위해서는 GSON 사용
			// json->자바 / 자바->json
			Gson gson = new Gson();
			Type movieType = new TypeToken<List<Movie>>() {}.getType();
			movieList = gson.fromJson(array.toJSONString(), movieType);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movieList;
	}
	
	// 국가 정보 조회
	public List getNationList() {
		String nationCdResponse = null;
		List<Nation> nationList = null;
		try {
			nationCdResponse = kobisOpenAPIRestService.getComCodeList(true,"2204");
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject)parser.parse(nationCdResponse);
			JSONArray array = (JSONArray)json.get("codes");
			Gson gson = new Gson();
			Type nationType = new TypeToken<List<Nation>>() {}.getType();
			nationList = gson.fromJson(array.toJSONString(), nationType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nationList;
	}
	
	// 영화 유형 조회
	public List getTypeList() {
		String movieTypeCdResponse = null;
		List<Type> movieTypeList = null;
		try {
			movieTypeCdResponse = kobisOpenAPIRestService.getComCodeList(true,"2201");
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject)parser.parse(movieTypeCdResponse);
			JSONArray array = (JSONArray)json.get("codes");
			Gson gson = new Gson();
			Type movieType = new TypeToken<List<MovieType>>() {}.getType();
			movieTypeList = gson.fromJson(array.toJSONString(), movieType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movieTypeList;
	}
	
	// 영화 1건 조회
	// API에서 DTO에 정보를 더 채워 반환
	public Movie getMovie(Movie movie) { // DAO가 list 반환: movieCd와 url만 보유
		
		MovieInfoResult movieInfoResult = null;
		
		try {
			movieInfoResult = new MovieAPIServiceImplService().getMovieAPIServiceImplPort().searchMovieInfo(key, movie.getMovieCd());
			movie.setMovieNm(movieInfoResult.getMovieInfo().getMovieNm());   // 영화이름
			movie.setOpenDt(movieInfoResult.getMovieInfo().getOpenDt()); 	 // 개봉일
			movie.setPrdtYear(movieInfoResult.getMovieInfo().getPrdtYear()); // 제작년도

			List<Director> directorList = new ArrayList<Director>(); // 감독을 채워넣을 List
			for(int i=0; i<movieInfoResult.getMovieInfo().getDirectors().getDirector().size(); i++) {
				String dname = movieInfoResult.getMovieInfo().getDirectors().getDirector().get(i).getPeopleNm();
				Director director = new Director();
				director.setPeopleNm(dname);
				directorList.add(director);
			}
			movie.setDirectors(directorList);
			
		} catch (OpenAPIFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movie;
	}
	
}
