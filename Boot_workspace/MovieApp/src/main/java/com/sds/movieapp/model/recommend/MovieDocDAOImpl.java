package com.sds.movieapp.model.recommend;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sds.movieapp.domain.MovieDoc;

@Repository
public class MovieDocDAOImpl implements MovieDocDAO {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List selectAll(Map map) {
		// TODO Auto-generated method stub
		
		List list = null;
		
		if(map == null) { // 페이징 처리 무시
			list = mongoTemplate.findAll(MovieDoc.class);
		} else {
			int startIndex = (int)map.get("startIndex");
			int rowCount = (int)map.get("rowCount");
			Query query = new Query().skip(startIndex).limit(rowCount);
			list = mongoTemplate.find(query, MovieDoc.class);
		}
		return list;
	}

	@Override
	public MovieDoc select(int movie_idx) {
		// TODO Auto-generated method stub
		
		Query query = new Query();
		query.addCriteria(Criteria.where("movie_idx").is(movie_idx));
		MovieDoc movieDoc = mongoTemplate.findOne(query, MovieDoc.class);
		
		return movieDoc;
	}

	@Override
	public void insert(MovieDoc movieDoc) {
		// TODO Auto-generated method stub
		
	}

}
