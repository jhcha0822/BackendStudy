package com.sds.movieadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.exception.MovieException;
import com.sds.movieadmin.exception.UploadException;
import com.sds.movieadmin.model.movie.MovieApiService;
import com.sds.movieadmin.model.movie.MovieService;

@RestController
public class RestMovieController {

	@Autowired
	private MovieApiService movieApiService;
	
	@Autowired
	private MovieService movieService;
	
	// 영화 검색
	@GetMapping("/search/movie")
	public List getList(Movie movie) {
		//System.out.println("국가 코드:"+movie.getRepNationCd());
		//System.out.println("영화 유형:"+movie.getMovieTypeCdArr()[0]);
		
		List movieList = movieApiService.getMovieList(movie);
		
		return movieList;
	}
	
	// 1건 등록
	@PostMapping("/movie")
	public ResponseEntity regist(Movie movie) {
		movieService.regist(movie);
		ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).build(); // 200
		return entity;
	}
	
	// 엑셀로 일괄 등록
	@PostMapping("/excel/movie")
	public ResponseEntity registExcel(Movie movie) {
		//MultipartFile file = movie.getFile();
		//System.out.println(file.getOriginalFilename()); //업로드된 파일명 출력 확인
		
		movieService.registExcel(movie);
		ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).build();
		
		return entity;
	}
	
	@ExceptionHandler(UploadException.class)
	public ResponseEntity handle(UploadException e) {
		ResponseEntity entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return entity;
	}
	
	@ExceptionHandler(MovieException.class)
	public ResponseEntity handle(MovieException e) {
		ResponseEntity entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return entity;
	}
}
