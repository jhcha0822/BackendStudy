// ���������� �ڹٴ� "��"�� ������ ���� ��
// JVM ���� �н�
class Bird{
	String name = "���޻�";
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
Ȥ�� Method   (Memory)
  static   |   stack   |   heap
------------------------------------------
           |           |    
  ���� �ڵ�   |  String  |   �ν��Ͻ�
   .java   |   args[]  |    
           |           |    
------------------------------------------
              Hard Disk

.java ����(�����ҽ�)�� �����ϴ� ���� HDD�� �����
�������� �ϰ� �ȴٸ� 
Java.exe�� ���� �� ���� ������ static���� �̵� (load)
����ΰ� main method�� ȣ��
args[0]�� ����
line�� �������� ���������� ���ÿ� ����
�ν��Ͻ��� �����Ǿ� heap ������ ����, �� �ּҰ� stack�� ����
�ν��Ͻ� �� ����� ȣ��Ǹ� �� ������ ����
�ݴ� }�� ������ stack�� ����� ��� ���������� �����
��, �ν��Ͻ��� heap�� ��������
������ �ݷ��Ͱ� �켱������ ���� �ν��Ͻ��� �˾Ƽ� ����
*/