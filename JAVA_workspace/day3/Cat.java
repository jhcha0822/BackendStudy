class Cat {
	String name = "네로";
	int age = 2;
	String color = "black";
	// Cat()이라는 매서드가 없는데 에러가 나지 않음
	// 시스템, 즉 컴파일러에 의해 정의된 생성자를 가리켜 디폴트 생성자라 함
	// 디폴트 생성자의 목적은 최소한의 관여를 통해 에러만 안나도록 존재함

	// 개발자가 생성자를 명시하면 더이상 컴파일러에 의한, 즉 에러방지용이었던 디폴트 생성자는 존재하지 않는다.

	// 생성자에 매개변수를 두어 다양한 인스턴스 생성
	// 생성자도 메서드이므로 매개변수 사용 가능
	// 단 생성자 메서드는 반환형을 두어서는 안된다
	public Cat(String name, int age, String color){
		this.name = name; // String name = "네로";
		this.age = age; // int age = 2;
		this.color = color; // String color = "black";
	}
}
