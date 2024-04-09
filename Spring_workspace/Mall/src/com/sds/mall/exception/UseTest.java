package com.sds.mall.exception;

public class UseTest {
	
	public static void main(String[] args) {
		ExceptionTest test = new ExceptionTest();
		
		try {
			test.cal();
		} catch (CalculateFailException e) {
			// TODO Auto-generated catch block
			System.out.println("에러 발생");
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
