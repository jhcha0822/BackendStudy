class UseAccount {
	public static void main(String[] args) {
		// �ν��Ͻ��� �ø���
		Account acc = new Account();
		acc.master="��";

		// ��� ���
		System.out.println(acc.master);

		// ���� ���������� ���Ƶξ����Ƿ� �������� ������� ����ؾ� �Ѵ�.
		acc.setBalance(700000000);
		System.out.println(acc.getBalance());

		// �ڹ� Ŭ������ ���������, �� �����ʹ� ��ȣ�����
		// ���� �ڹٿ����� ��������� �޼��忡 ���� ���������ڸ� �����Ͽ� �������� ����ó���� ����
		// public(��������) < protected(��Ӱ����� ��ü������ ���ٰ���) < defaulut(��Ӱ���+���� ���丮(��Ű��)�� ����) < private(������ ���� �����θ�)
	}
}
