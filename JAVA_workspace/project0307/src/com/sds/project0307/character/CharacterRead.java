package com.sds.project0307.character;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// 우리가 지금까지 사용했던 FileInputStream은 스트림의 분류 중 데이터 처리 단위를 기준으로 보면
// byte 기반의 스트림이다. 읽고 쓸 때 1byte씩 처리한다
// 파일복사와 같은 업무를 수행할 때 문제가 없다
// 영문을 대상으로 출력을 할 때도 문제가 없다 (1바이트임)
// 바이트 기반의 스트림으로 한글, 중국어, 일본어, 아랍어 등 유니코드 기반의 언어들을
// 실행중인 프로그램으로 1바이트씩 읽으면서 문자를 출력할 때는 문자가 깨져서 보인다
// 영미권 이외의 문자들은 2바이트가 모여 하나의 문자를 표현하고 있기 때문이다

// 만일 이 세상에 1바이트씩 처리하는 바이트 기반의 스트림만 지원한다면
// 실행중인 프로그램인 문서 편집기에서 한글로 이루어진 메모장 파일 등을 출력할 경우
// 한글은 깨져서 보이게 된다.

// SUN 사에서는 다국어를 지원하기 위해 stream중 2바이트를 묶어 하나의 문자로 해석할 수 있는 문자 기반 스트림을 지원
// 문자 기반 stream의 경우 접미어가 Reader나 Writer로 끝나는 경우가 많다
public class CharacterRead {
	FileReader reader; // 문서, "텍스트" 파일을 대상으로 한 스트림이며, 해당 파일에 존재하는 데이터가 영미권 이외의 문자라 하더라도
							 // 2바이트를 하나로 묶어서 이해할 수 있는 기능이 지원되므로 영미권 이외의 문자를 처리 가능
	// Reader 스트림은 문자를 1자씩 읽어들이므로, 문서파일의 용량이 크면 효율성이 떨어진다
	// 따라서 버퍼를 이용하여 문자를 모아서 문자열로 모은 후 읽어들이는 방법을 이용한다면
	// 읽어들이는 횟수를 줄여 효과적인 처리가 가능하다.
	// 자바에서 버퍼처리된 스트림은 접두어가 Buffered~가 붙는다
	BufferedReader buffr; // 문자 기반 스트림을 대상으로, 버퍼 기능을 지원하는 스트림
	
	public CharacterRead() {
		try {
			reader = new FileReader("D:\\MULTICAMPUS\\JAVA_workspace\\project0307\\res\\hello.txt");
			buffr = new BufferedReader(reader);
			// 데이터 읽기
			// int data = -1;
			String data = null;
			int count = 0;
			while(true) {
				//data = reader.read(); // 1바이트가 아닌 1문자를 읽어들인다.
				data = buffr.readLine(); // 한 줄을 읽어온다.
				count++;
				if(data == null) // if(data == -1)
					break;
				System.out.println(data+", cnt="+count); // (char)data
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(buffr != null) {
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
	public static void main(String[] args) {
		new CharacterRead();
	}
}
