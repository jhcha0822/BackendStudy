// ���� �ٸ� ��Ű���� �ִ� Dog Ŭ������ ����غ���

package use;

// �Ʒ��� �ڹ��� ���ΰǿ� ���� 'write once, run anywhere'
// import D:\MULTICAMPUS\JAVA_workspace\app0228\bin\animal\Dog.class

// OS�� ���� bin�� �ּҰ� ��ε�ĳ���õ�
import animal.Dog;

class UseDog {
	public static void main(String[] args) {
		Dog d = new Dog();
		System.out.println("name: "+d.getName());
	}
}
