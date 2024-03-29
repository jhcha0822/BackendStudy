package com.sds.mvcframework.movie.model;


// logic을 담당하는 Model
// 웹도 독립실행형 전용 클래스도 아닌 java의 중립적 로직 객체
public class MovieManager {

	public String getAdvice(String movie) {
		String msg = null;
		
		if(movie.equals("파묘"))
			msg = "공포";
		else if(movie.equals("존윅"))
			msg = "액션";
		else if(movie.equals("탑건"))
			msg = "액션";
		else if(movie.equals("듄2"))
			msg = "SF";
		return msg;
	}
	
}
