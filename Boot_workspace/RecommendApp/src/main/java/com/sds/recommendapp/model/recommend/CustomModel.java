package com.sds.recommendapp.model.recommend;

import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;

import com.sds.recommendapp.domain.CommentsDoc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

// 영화평 데이터를 이용한 데이터 모델 생성을 위한 객체
// 개발자가 정의한 자료형을 apache mahout에서 원하는 형태로 전환해 놓은 결과물 반환
@Slf4j
@Data
public class CustomModel {

	private DataModel dataModel; // 이 모델이 데이터를 이해하도록 코드 작성
	
	public CustomModel(int member_idx, List<CommentsDoc> commentsList) {
		// TODO Auto-generated constructor stub
		
		// <사용자ID, [{영화평점 1}, {영화평점 2}, ...]>
		// GenericPerference : {member_idx, movie_idx, score}, 영화평 한개의 정보를 가진 객체
		// Map<Long, List<GenericPreference>> preferMap = new HashMap();
		
		List<GenericPreference> preferList = new ArrayList<GenericPreference>();
		
		for(CommentsDoc commentsDoc : commentsList) {
			GenericPreference prefer = null; 
			prefer = new GenericPreference(commentsDoc.getMember_idx(), commentsDoc.getMovie_idx(), commentsDoc.getScore());
			preferList.add(prefer);
		}
		
		// 생성된 리스트를 map에 넣어주되 회원의 id를 key값으로 사용
		// preferMap.put((long)member_idx, preferList);
		
		FastByIDMap<PreferenceArray> userData = new FastByIDMap<PreferenceArray>();
		userData.put(member_idx, new GenericUserPreferenceArray(preferList));
		
		dataModel = new GenericDataModel(userData);
	}
	
}
