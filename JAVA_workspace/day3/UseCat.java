class UseCat {
	public static void main(String[] args) {
		int x = 5; // 기본 자료형
		// Cat c = new Cat();

		// Cat()이라는 매서드가 없는데 에러가 나지 않음
		// 시스템, 즉 컴파일러에 의해 정의된 생성자를 가리켜 디폴트 생성자라 함
		// 디폴트 생성자의 목적은 최소한의 관여를 통해 에러만 안나도록 존재함
		
		Cat c1 = new Cat("네로", 2, "Black");
		Cat c2 = new Cat("나비", 3, "Yellow");
		Cat c3 = new Cat("시바", 4, "Gray");
		Cat c4 = new Cat("예드", 5, "Orange");

		System.out.println(c1.name);
		System.out.println(c2.name);
		System.out.println(c3.name);
		System.out.println(c4.name);
	}
}
