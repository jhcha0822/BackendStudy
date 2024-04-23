package com.sds.testapp.model.notice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sds.testapp.domain.Notice;

// Mapper에서 이 DAO를 접근 가능하게
@Mapper
public interface NoticeDAO {
	public int getTotalCount();
	public List selectAll(Map map);
	public Notice select(int notice_idx);
	public int insert(Notice notice);
	public int update(Notice notice);
	public int delete(Notice notice);
	public int updateHit(int notice_idx);
}
