package site0319;

public class StringTest {
	public static void main(String[] args) {
		String s1 = "abc"; // 스트링 객체 생성 (묵시적: implicit)
		
		// String은 자바에서 객체이기 때문에 또한 일반클래스로 지원: new 연산자로 생성 가능
		String s2 = new String("abc"); // (명시적: explicit)
		
		System.out.println(s1 == s2); // false 반환
		
		// s1은 heap 내의 상수풀(constant pool)에서 관리
		// s2는 일반 객체화되어 일반 heap에서 관리
		
		// 자바는 레퍼런스변수끼리의 비교 시 내용이 아닌 주소값을 비교
		
		String m1 = "abc";
		String m2 = new String("abc");
		
		System.out.println(s1 == m1); // true 반환: 하지만 주소값이 같아서
		
		// 상수풀에서는 같은 값의 중복을 허용하지 않음; 따라서 같은 주소를 재사용하기 때문에 true 반환
		
		// 따라서 객체간 내용을 비교하기 위해서는 최상위 객체인 Object의 equals()를 사용
		System.out.println(s1.equals(m1)); // true: 내용이 같으므로
		
		//스트링 2번째 특징 
		//불변(immutable)의 특징 즉 수정불가한 상수이다
		String s="banana";
		for(int i=1;i<=100;i++) {
			s = s+i;
			System.out.println(s);
		}
		//위와 같이  스트링 객체는 변경이 불가한 상수이므로, 반복문에서 + 연결자로 문자열을 추가할경우
		//수정되는 것이 아니라, 그냥 새로운 문자객체를 생성해버린다..
		
		//해결책) 수정가능한 문자열 객체처리를 이용하면 됨..StringBuilder, StringBuffer 사용 
		StringBuilder sb = new StringBuilder(); //String 클래스 아님 
		
		String test="";
		for(int i=1;i<10;i++) {
			sb.append("우");
		}
		System.out.println(sb.toString());
		
	}
}
