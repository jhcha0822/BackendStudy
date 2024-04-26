package com.sds.movieadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.model.movie.MovieApiService;

@RestController
public class RestMovieController {

	@Autowired
	private MovieApiService movieApiService;
	
	// 영화 검색
	@GetMapping("/search/movie")
	public List getList(Movie movie) {
		System.out.println("국가 코드:"+movie.getRepNationCd());
		System.out.println("영화 유형:"+movie.getMovieTypeCdArr()[0]);
		
		List movieList = movieApiService.getMovieList(movie);
		
		return movieList;
	}
	
	@PostMapping("/movie")
	public ResponseEntity regist(Movie movie) {
		
		return null;
	}
	
}
