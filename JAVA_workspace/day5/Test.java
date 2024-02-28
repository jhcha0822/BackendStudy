class Test{
	public static void main(String[] args) {
		// JS처럼 자바도 배열의 길이를 유동적으로 늘리는 방법이 존재하나? --> 없다
		// 자바에서 배열의 크기는 조정이 불가함
		// 컬렉션 프레임워크 라이브러리에서 다양한 자료구조를 지원함
		// 자바에서 외부 라이브러리를 이용하려면 API 문서 참조
		// https://docs.oracle.com/javase/8/docs/api/
		
		// 현재 우리 디렉토리에는 String.class 파일이 존재하지 않더라도
		// java.lang이라는 디렉토리에 존재하는 String.class는 자동으로 위치가 현재 개발중인 클래스로 인식되어져 있다
		String str = "나는 문어";
		String[] result = str.split(" ");
		
		for(int i=0; i<result.length; i++)
			System.out.println(result[i]);
	}
}
