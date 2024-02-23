// int year=0; 
// 자바는 전역변수 없이 모두 클래스 안에서 이루어짐

class Book{
	// 멤버변수
	// 인스턴스 생성 후 접근 가능
	int price=300;

	public static void main(String[] args){
		// price = 500;
		Book b = new Book();
		System.out.println(b.price);
	}
}