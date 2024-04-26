package com.sds.movieadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.model.movie.MovieApiService;

@Controller
public class MovieController {
	
	@Autowired
	private MovieApiService movieApiService;

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
	
	
}
