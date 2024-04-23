package com.sds.testapp.model.notice;

import java.util.List;
import java.util.Map;

import com.sds.testapp.domain.Notice;

public interface NoticeService {
	public int getTotalCount();
	public List selectAll(Map map);
	public Notice select(int notice_idx);
	public void insert(Notice notice);
	public void update(Notice notice);
	public void delete(Notice notice);
}
