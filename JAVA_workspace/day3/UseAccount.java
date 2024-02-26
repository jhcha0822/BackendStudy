class UseAccount {
	public static void main(String[] args) {
		// 인스턴스에 올리기
		Account acc = new Account();
		acc.master="나";

		// 결과 출력
		System.out.println(acc.master);

		// 변수 직접접근을 막아두었으므로 간접적인 방법으로 사용해야 한다.
		acc.setBalance(700000000);
		System.out.println(acc.getBalance());

		// 자바 클래스의 멤버변수는, 즉 데이터는 보호대상임
		// 따라서 자바에서는 멤버변수나 메서드에 대해 접근제한자를 제공하여 개발자의 보안처리를 지원
		// public(완전공개) < protected(상속관계의 객체끼리만 접근가능) < defaulut(상속관계+같은 디렉토리(패키지)만 제공) < private(변수를 가진 스스로만)
	}
}
