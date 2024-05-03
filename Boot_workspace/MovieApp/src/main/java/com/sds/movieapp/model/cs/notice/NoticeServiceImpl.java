package com.sds.movieapp.model.cs.notice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.movieapp.domain.Notice;
import com.sds.movieapp.exception.NoticeException;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return noticeDAO.selectCount();
	}

	@Override
	public List selectAll(Map map) {
		// TODO Auto-generated method stub
		return noticeDAO.selectAll(map);
	}
	
	@Override
	public Notice select(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDAO.select(notice);
	}

	@Override
	public void insert(Notice notice) throws NoticeException {
		// TODO Auto-generated method stub
		noticeDAO.insert(notice);
	}

	@Override
	public void update(Notice notice) throws NoticeException {
		// TODO Auto-generated method stub
		noticeDAO.update(notice);
	}

	@Override
	public void delete(Notice notice) throws NoticeException {
		// TODO Auto-generated method stub
		noticeDAO.delete(notice);
	}

}
