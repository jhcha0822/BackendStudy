class Phone {
	
	int memory=300; //A
	int price; //B
	
	public void call(){
		int n; //C
		System.out.println("n�� ���� "+n); //D ���������� �ʱ�ȭ�� ������ �ܰ迡�� ��Ƴ�
	}

	public static void main(String[] args) {
		// price = 50; // ���� �Ұ���
		Phone p = new Phone();
		p.call();
	}

}
