class Phone {
	
	int memory=300; //A
	int price; //B
	
	public void call(){
		int n; //C
		System.out.println("n의 값은 "+n); //D 지역변수의 초기화는 컴파일 단계에서 잡아냄
	}

	public static void main(String[] args) {
		// price = 50; // 변경 불가능
		Phone p = new Phone();
		p.call();
	}

}
