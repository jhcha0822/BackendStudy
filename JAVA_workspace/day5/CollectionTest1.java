// 현재 클래스와 다른 위치에 있는 외부 클래스를 사용하려면
// 해당 클래스의 위치를 등록해야함
import java.util.ArrayList;

class CollectionTest1 {
	// 자바 배열의 특징
	// 1. 한 종류의 데이터만 담을 수 있다
	// 2. 크기가 고정되어 있다. 따라서 동적 변경 불가능
	// 3. 모든 배열은 객체로 본다. 따라서 배열변수명을 출력하면 주소값이 출력됨

	// 배열의 특징 중, 크기가 고정되어 있다는 것은 동적변경을 목적으로 하는 프로그램에서의 한계점
	// ex) 총알을 발사할때마다 배열에 담기
	//     Bullet[] bulletArrat = new Bullet[크기고정];

	// 자바의 라이브러리 중 collection framework이라 불리는 라이브러리를 사용하여 해결하기(class를 모아둔 집합)
	// collection framework이란:
	// 객체를 모아서 처리할 때 유용한 기능을 지원하는 자바의 라이브러리 패키지 (java.util 패키지[디렉토리]에 존재)

	// 컬렉션을 이루는 객체들의 유형
	// 관심대상은 오직 객체이고, 자바의 기본자료형은 이 라이브러리의 대상이 아님
	// 1. 순서가 있는 유형 (List형)
	// 2. 순서가 없는 유형 (Set형)
	// 3. key-value로 이루어진 유형 (Map형)

	// import: 나와는 다른 위치에 있는 객체를 받아오는 방식
	public static void main(String[] args) {
		// Collection framework의 순서있는 객체들 집합을 제어하는 객체, List의 자식인 ArrayList
		// List는 우리가 사용해왔던 배열과 거의 같지만
		// 1. 크기가 동적으로 변경될 수 있다.
		// 2. 오직 객체만을 담을 수 있다.
		// 3. 객체들을 섞어서 담을 수 있다.
		ArrayList list = new ArrayList();

		list.add("apple"); // js의 push와 동일
		list.add("banana");
		list.add(new Dog("티코", 5));

		System.out.println("List의 크기: "+list.size());
	}
}
