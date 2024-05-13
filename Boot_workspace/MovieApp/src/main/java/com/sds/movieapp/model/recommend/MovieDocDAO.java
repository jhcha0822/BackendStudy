package com.sds.movieapp.model.recommend;

import java.util.List;
import java.util.Map;

import com.sds.movieapp.domain.MovieDoc;

public interface MovieDocDAO {

	public int selectCount();       	   // 총 레코드 수 확인
	public List selectAll(Map map); 	   // 모든 영화 조회
	public MovieDoc select(int movie_idx); // 영화 1건 조회
	public void insert(MovieDoc movieDoc); 
	
}
