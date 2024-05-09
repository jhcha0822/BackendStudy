package com.sds.movieapp.model.recommend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.movieapp.domain.CommentsDoc;
import com.sds.movieapp.domain.MovieDoc;
import com.sds.movieapp.model.comments.CommentsDocDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecommendServiceImpl implements RecommendService {

	private DataModel dataModel;
	
	@Autowired
	private CommentsDocDAO commentsDocDAO;
	
	@Override
	public List getList(int member_idx) {
		// TODO Auto-generated method stub
		
		// 유저의 모든 영화평 수집
		List<CommentsDoc> commentsList = commentsDocDAO.selectAllByMember(member_idx);
		Map<Long, MovieDoc> metadataMap = new HashMap(); // 해당 영화평에 대한 영화를 모아둘 map
		
		// 위 순수 List를 Mahout이 지원하는 전용 List로 변환
		// model은 우리가 정의(영화평 정보를 이용하여 생성)
		CustomModel model = new CustomModel(member_idx, commentsList);
		DataModel dataModel = model.getDataModel();
		
		PreferenceArray preferenceArray = null;
		
		try {
			// 유저가 평가한 영화평 목록
			preferenceArray = dataModel.getPreferencesFromUser((long)member_idx);
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//영화평마다 1:1 대응하는 영화 메터데이터(감독, 국가, 장르, 배우) 맵에 채워넣기
		for(int i=0; i<preferenceArray.length(); i++) {
			log.debug("평을 남겼던 영화 번호: "+preferenceArray.get(i).getItemID());
		}
		
		return null;
	}

}
