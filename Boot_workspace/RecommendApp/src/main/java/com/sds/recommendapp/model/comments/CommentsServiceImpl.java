package com.sds.recommendapp.model.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.recommendapp.domain.CommentsDoc;
import com.sds.recommendapp.domain.MovieDoc;
import com.sds.recommendapp.domain.SentimentDic;
import com.sds.recommendapp.model.recommend.SentimentDicDAO;

import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.util.common.model.Pair;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentsServiceImpl implements CommentsService{

	@Autowired
	private Komoran komoran;

	@Autowired
	private SentimentDicDAO sentimentDicDAO;
	
	@Autowired
	private CommentsDocDAO commentsDocDAO;
	
	@Override
	public void registComments(CommentsDoc commentsDoc, MovieDoc movieDoc) {
		// TODO Auto-generated method stub
		
		commentsDoc.getContent().replaceAll("[^a-zA-z0-9가-힣\\s]", ""); // 일반적 문자를 제외하고 , . 등을 제거
		
		// komoran에게 영화평을 형태소 단위로 분할시키기
		KomoranResult result = komoran.analyze(commentsDoc.getContent());
		List<Pair<String, String>> resultList = result.getList();
		float score = 0;
		for(Pair<String, String> pair : resultList) {
			// log.debug(pair.getFirst()+", "+pair.getSecond()); 산낙지, NNG
			// 감성사전 조회
			SentimentDic sentimentDic = sentimentDicDAO.select(pair.getFirst()+"/"+pair.getSecond());
			if(sentimentDic != null) {
				score += sentimentDic.getPOS()-sentimentDic.getNEG();
			} else {
				log.debug("사전에 존재하지 않는 요소");
			}
		}
		log.debug("영화평 내용의 분석 결과: "+score);
		commentsDoc.setScore(score);
		commentsDocDAO.insert(commentsDoc); // 작성자(member_idx)+영화평(content)+영화명(movie_idx)
	}
	
}
