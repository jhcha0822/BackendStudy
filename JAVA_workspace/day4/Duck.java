class Duck extends Bird {
	
	public void quack(){
		System.out.println("��\n");
	}

	public Duck(){
		// ��Ӱ��迡�� super() ����Ʈ �����ڸ� �ڵ� ȣ��
		
		// �θ� �����ڿ� �Ű������� ������ ���
		// �����Ϸ��� ���� �ڵ�ȣ�� ��� �����ڰ� ���� ������ ȣ��
		super("White");
		System.out.println("���� ����\n");
	}
}
