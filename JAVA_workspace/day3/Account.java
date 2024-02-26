class Account {
	// 자바 클래스의 멤버변수는, 즉 데이터는 보호대상임
	// 따라서 자바에서는 멤버변수나 메서드에 대해 접근제한자를 제공하여 개발자의 보안처리를 지원
	// public(완전공개) < protected(상속관계의 객체끼리만 접근가능) < defaulut(상속관계+같은 디렉토리(패키지)만 제공) < private(변수를 가진 스스로만)
	String bank="신한";
	private int balance=50000000;
	private String account_num="123456789";
	String master="홍길동";

	// private으로 변수를 막아놓으면 선의의 업무처리까지 불가능함
	// 따라서 메서드를 통해 간접적으로 접근하여 처리
	//	private 묶여진 변수를 읽게 할 수 있도록 제공하는 메서드 정의 패턴: getter
	//  변수를 변경할 수 있도록 제공되는 메서드 정의 패턴: setter

	// 메서드는 외부에서 접근할 수 있어야 하므로 public으로 풀어두자
	public int getBalance(){
		return balance;
	}

	public void setBalance(int balance){
		// 매개변수와 멤버변수명이 같을때는 멤버변수앞에 this를 명시해준다
		// this란 클래스로부터 탄생된 인스턴스가 자기 자신을 가리키는 레퍼런스 변수
		this.balance = balance;
	}

	// 객체지향언어에서 클래스의 중요 변수들을 private로 묶어버리고
	// 이에 대한 접근방법을 public 메서드를 통해 사용을 제공하는 메서드 정의 기법을 캡슐화, 은닉화(encapsulation)이라 한다.

	public String getAccount_num(){
		return account_num;
	}

	public void setAccount_num(String account_num){
		this.account_num = account_num
	}
}
