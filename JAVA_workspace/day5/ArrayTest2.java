class ArrayTest2 {
	public static void main(String[] args) {
		// JS에서 선언과 동시에 값을 할당하는 방법은 아래와 같음
		// let arr = ["사과", "포도", "오렌지"];
		// 자바도 이런 식의 방식을 허용
		String[] arr = {"사과", "포도", "오렌지"}; // 객체의 표현이므로 {}
		// String형이 아닌 자료형은 대입 불가
		// JS는 섞어 쓸 수 있음
		
		for(int i=0; i<arr.length; i++)
			System.out.println(arr[i]);

		// JDK5부터 집합형 데이터는 개선된 루프(improved loop)를 지원함
		// for(한 요소 : 집합형)
		for(String fruit : arr) // 속도는 떨어지나 데이터처리에 유리
			System.out.println(fruit);
	
	}
}
