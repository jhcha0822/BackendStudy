class ArrayTest1 {
	public static void main(String[] args) {
		// �ڹٴ� �Ϲ����� ���α׷��� ���(c, c#)ó�� �迭�� ����ÿ� �ݵ�� �� ũ�⸦ ����ؾ� ��
		// �ڵ������� �þ�� js�ʹ� Ʋ���� ��������
		// ���� �Ϲ����� ���� ����� ���������� �ݵ�� �ڷ��� ���
		int[] arr = new int[4];
		System.out.println("�迭�� ũ��� "+arr.length);

		// ��� �迭�� ��ü�� ó���Ǳ⶧���� �迭 ���� ��ü�� ����ϸ� �ּҰ��� ��µ�
		System.out.println("�迭�� �ּҴ� "+arr);

		// �ڹ��� ��� �ڷ������� �迭�� ������ �� �ִ�
		// �ڹٿ��� �ڷ����� �⺻�ڷ���(����, ����, ����) + ��ü�ڷ���
		boolean[] arr2 = new boolean[3];
		char[] arr3 = new char[5];

		// ��ü�ڷ������� �迭 ����
		String[] arr4 = new String[3];
		arr4[0] = "���";
		arr4[1] = "����";
		arr4[2] = "�ٳ���";
		for(int i=0; i<arr4.length; i++)
			System.out.println(arr4[i]);

		// ����ڰ� ������ Dog ���� �迭�� ������ ����
		Dog[] arr5 = new Dog[3];

		Dog d1 = new Dog("�޸�", 3); // �ν��Ͻ� ����
		Dog d2 = new Dog("�ǹ�", 5);
		Dog d3 = new Dog("¯��", 8);
		
		arr5[0] = d1;
		arr5[1] = d2;
		arr5[2] = d3;

		for(int i=0; i<arr5.length; i++)
			System.out.println(arr5[i].name);
	}
}
