class Test{
	public static void main(String[] args) {
		// JSó�� �ڹٵ� �迭�� ���̸� ���������� �ø��� ����� �����ϳ�? --> ����
		// �ڹٿ��� �迭�� ũ��� ������ �Ұ���
		// �÷��� �����ӿ�ũ ���̺귯������ �پ��� �ڷᱸ���� ������
		// �ڹٿ��� �ܺ� ���̺귯���� �̿��Ϸ��� API ���� ����
		// https://docs.oracle.com/javase/8/docs/api/
		
		// ���� �츮 ���丮���� String.class ������ �������� �ʴ���
		// java.lang�̶�� ���丮�� �����ϴ� String.class�� �ڵ����� ��ġ�� ���� �������� Ŭ������ �νĵǾ��� �ִ�
		String str = "���� ����";
		String[] result = str.split(" ");
		
		for(int i=0; i<result.length; i++)
			System.out.println(result[i]);
	}
}
