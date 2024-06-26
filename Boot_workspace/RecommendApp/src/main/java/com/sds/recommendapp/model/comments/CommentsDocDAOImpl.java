package com.sds.recommendapp.model.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sds.recommendapp.domain.CommentsDoc;
import com.sds.recommendapp.exception.CommentsException;

@Repository
public class CommentsDocDAOImpl implements CommentsDocDAO {

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

	@Override
	public List selectAllByMember(int member_idx) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("member_idx").is(member_idx));
		return mongoTemplate.find(query, CommentsDoc.class);
	}

	@Override
	public CommentsDoc select(int member_idx) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
