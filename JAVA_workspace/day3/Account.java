class Account {
	// �ڹ� Ŭ������ ���������, �� �����ʹ� ��ȣ�����
	// ���� �ڹٿ����� ��������� �޼��忡 ���� ���������ڸ� �����Ͽ� �������� ����ó���� ����
	// public(��������) < protected(��Ӱ����� ��ü������ ���ٰ���) < defaulut(��Ӱ���+���� ���丮(��Ű��)�� ����) < private(������ ���� �����θ�)
	String bank="����";
	private int balance=50000000;
	private String account_num="123456789";
	String master="ȫ�浿";

	// private���� ������ ���Ƴ����� ������ ����ó������ �Ұ�����
	// ���� �޼��带 ���� ���������� �����Ͽ� ó��
	//	private ������ ������ �а� �� �� �ֵ��� �����ϴ� �޼��� ���� ����: getter
	//  ������ ������ �� �ֵ��� �����Ǵ� �޼��� ���� ����: setter

	// �޼���� �ܺο��� ������ �� �־�� �ϹǷ� public���� Ǯ�����
	public int getBalance(){
		return balance;
	}

	public void setBalance(int balance){
		// �Ű������� ����������� �������� ��������տ� this�� ������ش�
		// this�� Ŭ�����κ��� ź���� �ν��Ͻ��� �ڱ� �ڽ��� ����Ű�� ���۷��� ����
		this.balance = balance;
	}

	// ��ü������� Ŭ������ �߿� �������� private�� ���������
	// �̿� ���� ���ٹ���� public �޼��带 ���� ����� �����ϴ� �޼��� ���� ����� ĸ��ȭ, ����ȭ(encapsulation)�̶� �Ѵ�.

	public String getAccount_num(){
		return account_num;
	}

	public void setAccount_num(String account_num){
		this.account_num = account_num
	}
}
