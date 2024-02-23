class Q7{
	public static void main(String[] args){
		// System.out.println("당신이 호출 시 넘긴 배열의 길이는"+args.length);
		// java Q7 사과 딸기 바나나
		// 당신이 호출 시 넘긴 배열의 길이는3

		// 두 개의 숫자를 입력받아 두 수의 합을 구하는 코드를 완성하시오
		int sum = Integer.parseInt(args[0])+Integer.parseInt(args[1]);
		System.out.println("두 수의 합: "+sum);
	}

	// main()과 같이 main은 직접 호출하지 않고,
	// 시스템 java.exe가 호출한다.

}
