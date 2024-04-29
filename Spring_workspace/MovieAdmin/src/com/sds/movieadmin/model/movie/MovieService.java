package com.sds.movieadmin.model.movie;

import java.util.List;
import java.util.Map;

import com.sds.movieadmin.domain.Movie;

public interface MovieService {

	public int selectCount();
	public List selectAll(Map map);
	public void regist(Movie movie);
	public void registExcel(Movie movie);

}
