package stream.copy;

/*
파일을 복사
- Stream이란?
  강의 지류, 물줄기 등 흐르는 물을 의미
  컴퓨터에서는 물의 흐름이 아닌 데이터의 흐름을 의미
- 방향성에 따른 구분 (IO)
  Input: 실행중인 프로그램으로 데이터가 들어옴
  Output: 실행중인 프로그램에서 데이터가 나감
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.FileNotFoundException; // 예외도 import 해야함
import java.io.IOException;

public class ImageCopy {
	FileInputStream fis; // 파일을 대상으로 한 입력 스트림 객체
						 // 이 클래스를 실행중인 자바 프로그램으로 파일을 이루고 있는 바이트 데이터들을 읽어 들일 수 있음
	FileOutputStream fos; // 파일을 대상으로 한 출력 스트림 객체
						  // 이 클래스를 이용하면 실행중인 자바 프로그램에서 지정한 파일로 바이트 데이터를 출력할 수 있음(쓰기)

	String filename = "D:/MULTICAMPUS/JAVA_workspace/app0307/res/hello.txt";
	// "D:/MULTICAMPUS/JAVA_workspace/app0307/res/chicken.png"; // 역슬래쉬\는 윈도우만 사용. 자바는 중립적인 언어라 슬래쉬/ 또는 역슬래쉬 2개\\ 이용

	String destname = "/MULTICAMPUS/JAVA_workspace/app0307/res/hello_copy.txt";
	
	public ImageCopy() {
		// 문법적으로 문제가 없다 하여 언제나 안전한 실행을 보장하는 것이 아니다
		// 즉, 프로그램의 정상 수행을 방해하는 요인이 존재한다: 예외(Exception)
		// 개발자가 예외사항을 만들지 않으면 복구가 불가능하다

		// 프로그램의 정상 수행을 방해하는 요인에는 크게 2가지 존재
		// 1) 에러: 사람인 개발자가 감당할 수 없는 경우 ex) 정전, 하드웨어, 네트워크 오류 등 --> 프로그램의 관심 대상이 아님
		// 2) 예외: 프로그래머가 복구할 수 없으나, 프로그램의 비 정상적 흐름을 정상 흐름으로 돌려놓기가 가능함
		
		// 비정상 수행이 예상되는 코드를 try문을 감싸고
		// 우려가 된 상황이 발생하면 프로그램의 비정상 수행을 방치하지 않고, catch문으로 실행부를 진입시켜
		// 알맞은 처리를 할 수 있도록 지원하는 기술 [예외처리]
		// 중대하다고 판단되는 예외 사항은 SUN이 정해두었기에 개발자는 컴파일 시 컴파일러의 안내에 따라 적절한 예외처리 수행

		// try-catch문이 작성되지 않으면 컴파일이 불가능함

		int data = -1;

		try{ // 비 정상 수행이 예상되는 코드를 try로 감싸놓기
			fis = new FileInputStream(filename);
			fos = new FileOutputStream(destname);
			System.out.println("파일에 대한 입력스트림 생성");

			System.out.println("파일 읽기 성공");
			while(true) {
				data = fis.read();
				if(data == -1) // EOF
					break;
				// System.out.print((char)data);
				fos.write(data);
			}
			// try문에 스트림 해제를 넣어두면 catch로 빠져나갈 시 스트림이 닫히지 않음
			
			/*
			int data = fis.read(); // 1byte 읽기, 예외 발생
			System.out.print("파일 읽기 성공: "+(char)data);
			data = fis.read(); // read는 호출할 때 마다 다음 데이터로 포인터를 옮김
			System.out.print((char)data);
			*/

		} catch(FileNotFoundException e) { // 소괄호 안에는 컴파일 에러 내의 예외를 표현한 객체 전달. 우려한 상황이 발생하면 JVM이 해당 예외에 대한 정보를 객체인스턴스로 생성하여 catch 변수에 매개변수로 전달
			// 복구는 불가능하지만 사용자와의 신뢰를 위해 오류 안내 메시지 작성 (복구는 아님)
			System.out.println("파일의 경로를 확인해주세요.");
		} catch(IOException e) { // 다중 catch문, 읽거나 쓸 때 발생하는 오류 감시
			System.out.println("데이터 읽기 또는 쓰기 실패");
		} finally { // 사용했던 스트림을 닫기 (메모리에서 해제)
					// 반납은 해당 스트림이 메모리에 올라왔을때만(not null) 수행
				//sun에서는 close에도 예외처리를 강조
				try{
					if(fos != null)
						fos.close();
				} catch(IOException e) {
					e.printStackTrace(); // 콘솔에 출력되는 메시지, 일반 유저를 위한 것이 아님. 주로 엔지니어 또는 시스템관리자
				}
			if(fis != null) {
				try{
					fis.close(); // fis이 실패했을 경우, null을 참조하게 됨: NullPointerException
								 // 객체가 존재하지 않을 때 그 메서드에 대한 명령이 입력되었을 때
				} catch(IOException e) {
					e.printStackTrace();
					System.out.println("불편을 드려 죄송합니다.");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new ImageCopy();
	}
}
