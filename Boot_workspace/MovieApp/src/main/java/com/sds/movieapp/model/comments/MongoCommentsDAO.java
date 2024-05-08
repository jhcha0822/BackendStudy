package com.sds.movieapp.model.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.sds.movieapp.domain.CommentsDoc;
import com.sds.movieapp.exception.CommentsException;

@Repository
public class MongoCommentsDAO implements CommentsDAO {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void insert(CommentsDoc commentsDoc) throws CommentsException {
		try {
			mongoTemplate.insert(commentsDoc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CommentsException("mongoDB 영화평 입력 실패", e);
		}
	}
	
}
