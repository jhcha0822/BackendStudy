class Plane {
	String line = "747";
	String brand = "대한항공";
	int seat = 120;
	int height = 0;


	// 메서드들이 내용(코드)에 큰 차이가 없다면 메서드명을 짓기 위해 고생할 필요가 없다
	// 메서드명을 동일하게 사용할 수 있다
	// 하지만 내부 로직이 완벽히 동일하다면 중복처리, 오류가 난다
	// 즉 자바에서는 비슷한 로직일 경우 메서드명을 유지할 수 있는 메서드 정의 기법을 지원해준다 : 메서드 오버로딩(Overloading)
	// 메서드명은 동일하되, 전혀 구분이 불가하면 안되므로, 매개변수의 자료형과 갯수로 구분하는 기법

	public void fly(){
		height = 300;
	}

	public void fly(int height){
		this.height = height;
	}

	public void fly(int height, String line){
		height = 500;
	}

	public static void main(String[] args){
		Plane p = new Plane();
		p.fly();
		System.out.println(p.height);
		p.fly(500);
		System.out.println(p.height);
		p.fly(700, "northwest-900");
		System.out.println(p.height);
	}

}
