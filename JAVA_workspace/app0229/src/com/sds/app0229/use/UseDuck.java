
package com.sds.app0229.use;

import com.sds.app0229.bird.Duck;
import com.sds.app0229.bird.Bird;

class UseDuck {
	public static void main(String[] args) {		
		// 오리 인스턴스 생성
		Duck d = new Duck();

		// 부모의 name 사용
		System.out.println(d.name);

		// 부모의 메서드 사용
		d.eat();

		// 오리를 새라고 불러도 문제가 없다고 생각하자
		Bird bird = new Duck();

		System.out.println(bird.age);
	}
}
