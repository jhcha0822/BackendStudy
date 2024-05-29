package com.sds.recommendapp.model.recommend;

import com.sds.recommendapp.domain.SentimentDic;

// 특정 형태소에 대해 긍정/부정 여부를 반환하는 메서드
public interface SentimentDicDAO {
	
	public SentimentDic select(String word);

}