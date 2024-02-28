// 나와 다른 패키지에 있는 Dog 클래스를 사용해보기

package use;

// 아래는 자바의 슬로건에 위배 'write once, run anywhere'
// import D:\MULTICAMPUS\JAVA_workspace\app0228\bin\animal\Dog.class

// OS에 의해 bin의 주소가 브로드캐스팅됨
import animal.Dog;

class UseDog {
	public static void main(String[] args) {
		Dog d = new Dog();
		System.out.println("name: "+d.getName());
	}
}
