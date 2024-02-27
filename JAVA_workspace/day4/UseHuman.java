class UseHuman {
	public static void main(String[] args) {
		// 아시아인과 서양인은 실질적으로 메서드를 각각 1개씩만 보유하고 있다
		// 하지만 상속을 통해 부모의 자산도 접근할 수 있으므로 많은 속성과 메서드를 보유한 결과가 된다
		Asian as = new Asian();
		as.farmRice();
		as.eat();
		as.think();
	}
}
