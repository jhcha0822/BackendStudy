package com.sds.smokingproject.model.smokingarea;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.smokingproject.domain.SmokingArea;
import com.sds.smokingproject.hibernate.HibernateManager;

public class HibernateSmokingAreaDAO implements SmokingAreaDAO {

	@Autowired
	private HibernateManager manager;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		//하이버네이트의 세션 을 얻어와서 쿼리실행 
		Session session = manager.getSession();
		Query<SmokingArea> query = session.createQuery("FROM SmokingArea", SmokingArea.class); //모든 레코드 가져와서 DTO 자동으로 매핑
		List list = query.list(); //java.util의 List로 옮기자
		manager.release(session); //세션 돌려보내기
		
		return list;
	}

	@Override
	public SmokingArea select(int smokingArea_idx) {
		// TODO Auto-generated method stub
		//한건 가져오기
		Session session = manager.getSession();
		Query query = session.createQuery("FROM SmokingArea WHERE smokingArea_idx = :smokingArea_idx", SmokingArea.class);
		query.setParameter("smokingArea_idx", smokingArea_idx);
		SmokingArea smokingArea = (SmokingArea)query.uniqueResult();
		manager.release(session);
		
		return smokingArea;
	}

}
