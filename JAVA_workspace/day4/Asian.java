class Asian extends Human {
	public void farmRice(){
		System.out.println("쌀농사를 지어요");
	}

	// 개발자가 생성자를 정의하면 컴파일러는 더 이상 관여하지 않는다
	// 디폴트 생성자는 에러를 방지하기 위한 최소한의 관여
	public Asian(){
		// super(); 생략됨
		// 부모의 생성자에 매개변수가 존재해 존재하지 않는 생성자를 호출하러 간 꼴
		// 해결 1) super(2, 2); 명시
		// 해결 2) 오버로딩을 통해 생성자 Human(); 생성
	}
}
