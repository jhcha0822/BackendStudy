package com.sds.mall.exception;

public class ExceptionTest {

	// public static void main(String[] args) {
		
		// 예외 Exception
		// 프로그램의 정상 수행을 방해하는 요인
		// 1) 강요된 예외
		//	   코드 작성(컴파일)시 예외처리가 되지 않으면 넘어갈 수 없다
		//	   처리를 강제하는 SUN이 정의한 예외 (빨간줄)
		//    try-catch를 작성했던 부분
		// 2) 강요되지 않은 예외
		//    개발자가 처리하지 않아도 컴파일이 무사히 진행된다
		//    RunTimeException으로 대표됨
		//    개발자가 자신만의 업무내용에 맞는 예외를 정의하고 싶다면 RunTimeException을 재정의하여 커스터마이징
		
//		int[] arr = new int[3];
//		try {
//			arr[4] = 5;
//		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("배열의 범위 벗어남");
//			e.printStackTrace();
//		}
		
	public void cal() throws CalculateFailException {
		int[] arr = new int[3];
		
		try {
			arr[4] = 5;
		} catch (ArrayIndexOutOfBoundsException e) {
			// throw는 개발자가 예외를 일부러 발생시키는 명령어
			throw new CalculateFailException("배열의 범위 벗어남", e);
		}
	}	
}
