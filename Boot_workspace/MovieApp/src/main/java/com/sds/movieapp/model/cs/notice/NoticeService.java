package com.sds.movieapp.model.cs.notice;

import java.util.List;
import java.util.Map;

import com.sds.movieapp.domain.Notice;

public interface NoticeService {

	public int selectCount();
	public List selectAll(Map map);
	public Notice select(Notice notice);
	public void insert(Notice notice);
	public void update(Notice notice);
	public void delete(Notice notice);
	
}
