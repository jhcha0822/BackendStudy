package com.sds.movieapp.model.comments;

import java.util.List;

import com.sds.movieapp.domain.CommentsDoc;

public interface CommentsDocDAO {
	
	public void insert(CommentsDoc commentsDoc);
	public List selectAllByMember(int member_idx);
	public CommentsDoc select(int member_idx);
	
}
