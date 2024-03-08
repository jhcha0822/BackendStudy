package com.sds.project0308.keyboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// 파일을 대상으로 스트림을 제어함과 같이,
// 또 다른 매체인 키보드를 대상으로 스트림을 제어

// 입력 장치에는 키보드, 바코드 스캐너, 트랙볼, 타블렛, 복합기 스캐너 등의 다양한 장치가 존재
// 이를 자바로 제어할 수 있음 (1:1 대응하는 클래스를 지원하는 것이 아님)
// 하드웨어는 OS가 처리하기에 자바는 OS에서 만들어놓은 스트림을 연결하는 것

public class KeyBoardStream {

	
	
	public static void main(String[] args) {
		// 키보드와 입력스트림 연결
		// 키보드는 별도의 클래스로 지원되지 않기에 입력스트림 중 가장 최상위의 추상클래스인 입력스트림 객체로 받아옴
		InputStream is = System.in; // 이미 OS 차원에서 생성해 놓은 입력스트림을 얻어오자
		// OS 차원에서 얻어놓은 입력 스트림은 표준을 따르기 때문에 자바 언어가 별도의 하드웨어를 인식할 필요없이
		// 모든 디바이스에 대한 스트림은 InputStream으로 연결만 하면 됨
		
		InputStreamReader reader = new InputStreamReader(is); // 문자 기반의 입력 스트림
		
		BufferedReader buffr = new BufferedReader(reader);

		// 키보드로부터 1바이트 읽기
		//int data = -1;
		String msg = null; // 한 줄을 담을 문자열
		try {
			int count = 0;
			
			while(true) { // 대기 상태
				//data = is.read();
				//data = reader.read();
				msg = buffr.readLine();
				count++;
				//System.out.print((char)data+" , "+count); // 출력
				System.out.print(msg+" , "+count); // 출력
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
