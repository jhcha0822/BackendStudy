// Collection Framework에서 지원하는 객체 중, 
// 순서 없는 객체들의 집합을 다루는 Set

// Set의 특징
// 1. 순서 없이 모음
// 2. 중복 허용 X (덮어씀)
// 3. 오직 객체만을 담을 수 있다

import java.util.HashSet;

class SetTest {
	public static void main(String[] args) {
		HashSet set = new HashSet();

		set.add("BMW");
		set.add("AUDI");
		set.add("Benz");

		// 모든 요소들을 반복문으로 출력 가능할까?
		// 순서가 없기에 불가능

		// 순서 없는 것을 순서지어 내보내기
		// 1. Enumeration (enum)
		// 2. Iterator

		Iterator it = set.iterator();

		System.out.println(it);
	}
}
