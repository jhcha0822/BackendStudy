package com.sds.movieadmin.model.movie;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.exception.MovieException;

@Repository
public class MybatisMovieDAO implements MovieDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("Movie.selectCount");
	}
	
	@Override
	public List selectAll(Map map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("Movie.selectAll", map);
	}
	
	@Override
	public void insert(Movie movie) throws MovieException{
		// TODO Auto-generated method stub
		int result = sqlSessionTemplate.insert("Movie.insert", movie);
		if(result < 1)
			throw new MovieException("영화 등록 실패");
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		sqlSessionTemplate.delete("Movie.deleteAll");
	}
	
}
