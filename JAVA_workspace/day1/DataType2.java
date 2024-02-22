class DataType2{
	// java 호출 대상 main() 메서드 정의
	// java도 다른 프로그래밍 언어와 마찬가지로 문자 숫자 논리값 사용 가능
	// 단 자료형을 반드시 명시해야 하는 컴파일 언어이기에 자료형이 세분화되어 있다

	public static void main(String[] args) {
		// 문자: char 2byte
        // 숫자: 실수, 정수
        // - 실수: float(4)<double(8)
        // - 정수: byte(1)<short(2)<int(4)<long(8)
        // 논리값: boolean(1)

        byte b=127;

		// 자바에서는 모든 실수에 특별한 표시를 하지 않으면 디폴트값을 double로 간주
		// 따라서 강제로 float로 명시적 형변환
		float x = 0.96f;

		// 논리값
		boolean bool = true;

		// 문자형 (홑따옴표)
		char ch = 'Y';

		//

        System.out.println(x);
		
	}
}