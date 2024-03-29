package com.sds.mvcframework.blood.model;


// jsp 안에 이 혈액형 판단 코드를 두면
// 디자인인 jsp와 로직인 이 코드가 합쳐져 있어
// 유지보수 시 디자인을 버려야 하면 로직도 함께 버려야 한다
// 즉 재사용성이 없다(중복 개발)

// 따라서 나누어 둔다
// javaEE에서 model은 순수한 java 코드로 작성함이 원칙
// POJO(Plain Old Java Object)
public class BloodManager {
	
	public String getAdvice(String blood) {
		String msg = null;
		
		if(blood.equals("A"))
			msg = "소심함";
		else if(blood.equals("B"))
			msg = "고집있음";
		else if(blood.equals("AB"))
			msg = "귀가 얇음";
		else if(blood.equals("O"))
			msg = "가벼움";
		
		return msg;
	}
	
}
