class UseCat {
	public static void main(String[] args) {
		int x = 5; // �⺻ �ڷ���
		// Cat c = new Cat();

		// Cat()�̶�� �ż��尡 ���µ� ������ ���� ����
		// �ý���, �� �����Ϸ��� ���� ���ǵ� �����ڸ� ������ ����Ʈ �����ڶ� ��
		// ����Ʈ �������� ������ �ּ����� ������ ���� ������ �ȳ����� ������
		
		Cat c1 = new Cat("�׷�", 2, "Black");
		Cat c2 = new Cat("����", 3, "Yellow");
		Cat c3 = new Cat("�ù�", 4, "Gray");
		Cat c4 = new Cat("����", 5, "Orange");

		System.out.println(c1.name);
		System.out.println(c2.name);
		System.out.println(c3.name);
		System.out.println(c4.name);
	}
}
