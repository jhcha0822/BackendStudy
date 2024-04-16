package com.sds.mall.model.common;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

// 각 나라별 통화처리를 처리하는 공통 모델 객체
@Component
public class FormatManager {

	public String getCurrency(int price) {
		Locale korea = Locale.KOREA;
		NumberFormat f = NumberFormat.getInstance(korea); // 한국 통화에 맞는 포맷: 지정한 로케일에 맞는 통화 사용
		return f.format(price);
	}
	
	/*
	public static void main(String[] args) {
		FormatManager manager = new FormatManager();
		String result = manager.getCurrency(58000);
		System.out.println(result);
	}
	*/
}
