package com.sds.movieadmin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.movieadmin.common.Pager;
import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.model.movie.MovieApiService;
import com.sds.movieadmin.model.movie.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	private MovieApiService movieApiService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private Pager pager;

	// 영화진흥위원회가 제공하는 API에서 모든 영화 조회
	@GetMapping("/movie/api/list")
	public String getApiList(Movie movie, Model model) {
		
		List movieList = movieApiService.getMovieList(movie);
		model.addAttribute("movieList", movieList);
		
		return "admin/movie/list";
	}
	
	// 영화 등록
	@GetMapping("/movie/registform")
	public String getRegistForm(Model model) {
	
		// 국가 목록
		List nationList = movieApiService.getNationList();

		// 영화 유형
		List movieTypeList = movieApiService.getTypeList();
		
		model.addAttribute("nationList", nationList);
		model.addAttribute("movieTypeList", movieTypeList);
		
		return "admin/movie/regist";
	}
	
	// 엑셀 등록 폼 요청
	@GetMapping("/movie/excel/registform")
	public String getExcelForm() {
		return "admin/movie/upload";
	}
	
	// DB에 등록된 영화 목록 조회
	// currentPage 파라미터가 전송되어오지 않으면 디폴트 1
	@GetMapping("/movie/site/list")
	public String getSiteList(Model model, @RequestParam(value="currentPage", defaultValue="1") int currentPage) {
		
		pager.init(movieService.selectCount(), currentPage); // 페이징 처리 계산
		
		Map map = new HashMap();
		map.put("startIndex", pager.getStartIndex()); // n번째부터
		map.put("rowCount", pager.getPageSize());	  // n개 가져오기
		
		List movieList = movieService.selectAll(map);
		model.addAttribute("movieList", movieList);
		
		return "admin/movie/site_list";
	}
	
}
