package com.sds.movieapp.model.movie;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.movieapp.domain.Movie;
import com.sds.movieapp.exception.MovieException;
import com.sds.movieapp.exception.UploadException;


@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private MovieApiService movieApiService;
	
	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return movieDAO.selectCount();
	}

	@Override
	public List selectAll(Map map) {
		// TODO Auto-generated method stub
		List<Movie> siteMovieList = movieDAO.selectAll(map);
		// 영화코드 이용하여 API 조회 -> 빈 정보를 DTO에 넣기
		for(Movie movie : siteMovieList) {
			// OpenAPI 호출 객체에게 Movie DTO를 맡겨 정보를 더 받자
			movieApiService.getMovie(movie);
		}
		return siteMovieList;
	}
	
	@Override
	public List getMovieTypeList() {
		// TODO Auto-generated method stub
		return movieApiService.getTypeList();
	}

	@Override
	public Movie select(int movie_idx) {
		// TODO Auto-generated method stub
		Movie movie = movieDAO.select(movie_idx);
		
		// 정보를 채워넣기
		movieApiService.getMovie(movie);
		
		return movie;
	}

	
}
