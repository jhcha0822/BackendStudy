class ArrayTest2 {
	public static void main(String[] args) {
		// JS���� ����� ���ÿ� ���� �Ҵ��ϴ� ����� �Ʒ��� ����
		// let arr = ["���", "����", "������"];
		// �ڹٵ� �̷� ���� ����� ���
		String[] arr = {"���", "����", "������"}; // ��ü�� ǥ���̹Ƿ� {}
		// String���� �ƴ� �ڷ����� ���� �Ұ�
		// JS�� ���� �� �� ����
		
		for(int i=0; i<arr.length; i++)
			System.out.println(arr[i]);

		// JDK5���� ������ �����ʹ� ������ ����(improved loop)�� ������
		// for(�� ��� : ������)
		for(String fruit : arr) // �ӵ��� �������� ������ó���� ����
			System.out.println(fruit);
	
	}
}
