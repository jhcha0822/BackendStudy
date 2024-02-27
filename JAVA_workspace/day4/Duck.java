class Duck extends Bird {
	
	public void quack(){
		System.out.println("꽥\n");
	}

	public Duck(){
		// 상속관계에서 super() 디폴트 생성자를 자동 호출
		
		// 부모 생성자에 매개변수가 존재할 경우
		// 컴파일러에 의한 자동호출 대신 개발자가 직접 생성자 호출
		super("White");
		System.out.println("나는 오리\n");
	}
}
