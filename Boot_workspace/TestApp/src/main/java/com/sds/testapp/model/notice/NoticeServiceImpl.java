package com.sds.testapp.model.notice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.testapp.domain.Notice;
import com.sds.testapp.exception.NoticeException;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return noticeDAO.getTotalCount();
	}
	
	@Override
	public List selectAll(Map map) {
		// TODO Auto-generated method stub
		return noticeDAO.selectAll(map);
	}

	@Override
	public Notice select(int notice_idx) {
		// TODO Auto-generated method stub
		noticeDAO.updateHit(notice_idx); // 조회수 증가
		Notice notice = noticeDAO.select(notice_idx);
		return notice;
	}

	@Override
	public void insert(Notice notice) throws NoticeException {
		// TODO Auto-generated method stub
		int result = noticeDAO.insert(notice);
		if(result < 1) { // 예외처리는 서비스에서: DAO는 void 형
			throw new NoticeException("글 등록 실패");
		}
	}

	@Override
	public void update(Notice notice) throws NoticeException {
		// TODO Auto-generated method stub
		int result = noticeDAO.update(notice);
		if(result < 1) {
			throw new NoticeException("글 수정 실패");
		}
	}

	@Override
	public void delete(Notice notice) throws NoticeException {
		// TODO Auto-generated method stub
		int result = noticeDAO.delete(notice);
		if(result < 1) {
			throw new NoticeException("글 삭제 실패");
		}
	}
}
