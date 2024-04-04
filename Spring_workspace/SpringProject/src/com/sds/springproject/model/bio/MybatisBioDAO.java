package com.sds.springproject.model.bio;

import org.apache.ibatis.session.SqlSession;

import com.sds.springproject.domain.Bio;
import com.sds.springproject.mybatis.MybatisManager;

public class MybatisBioDAO implements BioDAO{

	/*
	private MybatisManager manager;
	
	public void setManager(MybatisManager manager) {
		this.manager = manager;
	}
	*/
	
	public int insert(Object obj, Bio bio) {
		int result = 0;
		
		// SqlSession sqlSession = null;
		SqlSession sqlSession = (SqlSession)obj;
		
		// sqlSession = manager.getSqlSession();
		result = sqlSession.insert("Bio.insert", bio);
		
		// if(result>0)
			// sqlSession.commit();
		
		// manager.release(sqlSession);
		
		return result;
	}

}
