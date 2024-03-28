package com.sds.dataroom.excel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Collection Framework: 객체를 모아서 처리할 때 유용한 기능을 지원하는 자바의 API
// java.util 패키지
// 오직 객체자료형만을 다룸. 기본 자료형은 해당 X
// 1) List: ordered
// 2) Set: unordered
// 3) Map: key-value

public class CollectionTest {

	public static void main(String[] args) {
		// 순서 없는 집합 ex) 과자
		Set set = new HashSet();
		set.add("사과");
		set.add("딸기");
		set.add("포도");
		set.add("레몬");
		set.add("바나나");
		
//		for(int i=0; i<set.size(); i++) {
//			set.get(i); // 순서가 없어 반복문의 대상이 될 수 없다
//		}
		
		Iterator<String> it = set.iterator(); // 빨때 꽂아서 일렬로 정렬
		// it.hasNext(); // 현재 내 위치 다음의 요소가 존재한다면 true 반환
		while(it.hasNext()) {
			String fruit = it.next();
			System.out.println(fruit);
		}
	}
}
