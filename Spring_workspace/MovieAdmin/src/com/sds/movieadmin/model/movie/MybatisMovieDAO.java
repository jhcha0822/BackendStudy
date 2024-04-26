package com.sds.movieadmin.model.movie;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.movieadmin.domain.Movie;

@Repository
public class MybatisMovieDAO implements MovieDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(Movie movie) {
		// TODO Auto-generated method stub
		int result = sqlSessionTemplate.insert("Movie.insert", movie);
		
	}
	
}
