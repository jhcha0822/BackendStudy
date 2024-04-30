package com.sds.movieapp.model.movie;

import java.util.List;
import java.util.Map;

import com.sds.movieapp.domain.Movie;

public interface MovieService {

	public int selectCount();
	public List selectAll(Map map);
	
	// 영화 유형 가져오기: 맨 위 카테고리바 채울 용도
	public List getMovieTypeList();
	
	public Movie select(int movie_idx);

}
