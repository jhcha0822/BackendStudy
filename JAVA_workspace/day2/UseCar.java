// Car.java에 실행부가 없어 이 클래스에서 실행을 담당함

class UseCar{
	public static void main(String[] args){
		// 타 클래스인 Car를 사용하려면 먼저 해당 파일이 컴파일되어 .class로 만들어야 됨
		Car c = new Car();
		
		int r = c.getRandom();

		System.out.println(r);
	}
}
