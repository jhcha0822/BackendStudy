// 전통적으로 자바는 "새"로 설명을 많이 함
// JVM 구조 학습
class Bird{
	String name = "종달새";
	String color = "red";
	int age = 3;

	public static void main(String[] args){
		int price = 5;
		Bird b1 = new Bird();
		Bird b2 = new Bird();

		b1.age = 7;

		System.out.println(price);
		System.out.println(b2.age);


	}
}

/*
혹은 Method   (Memory)
  static   |   stack   |   heap
------------------------------------------
           |           |    
  원본 코드   |  String  |   인스턴스
   .java   |   args[]  |    
           |           |    
------------------------------------------
              Hard Disk

.java 파일(원본소스)을 저장하는 순간 HDD에 저장됨
컴파일을 하게 된다면 
Java.exe로 실행 시 기계어 버전이 static으로 이동 (load)
실행부가 main method를 호출
args[0]이 생성
line을 내려가며 지역변수가 스택에 저장
인스턴스가 생성되어 heap 영역에 저장, 그 주소가 stack에 저장
인스턴스 내 멤버가 호출되면 힙 안으로 참조
닫는 }를 만나면 stack에 저장된 모든 지역변수가 사라짐
단, 인스턴스는 heap에 남아있음
가비지 콜렉터가 우선순위가 낮은 인스턴스를 알아서 수거
*/