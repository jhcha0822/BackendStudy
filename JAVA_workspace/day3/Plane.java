class Plane {
	String line = "747";
	String brand = "�����װ�";
	int seat = 120;
	int height = 0;


	// �޼������ ����(�ڵ�)�� ū ���̰� ���ٸ� �޼������ ���� ���� ����� �ʿ䰡 ����
	// �޼������ �����ϰ� ����� �� �ִ�
	// ������ ���� ������ �Ϻ��� �����ϴٸ� �ߺ�ó��, ������ ����
	// �� �ڹٿ����� ����� ������ ��� �޼������ ������ �� �ִ� �޼��� ���� ����� �������ش� : �޼��� �����ε�(Overloading)
	// �޼������ �����ϵ�, ���� ������ �Ұ��ϸ� �ȵǹǷ�, �Ű������� �ڷ����� ������ �����ϴ� ���

	public void fly(){
		height = 300;
	}

	public void fly(int height){
		this.height = height;
	}

	public void fly(int height, String line){
		height = 500;
	}

	public static void main(String[] args){
		Plane p = new Plane();
		p.fly();
		System.out.println(p.height);
		p.fly(500);
		System.out.println(p.height);
		p.fly(700, "northwest-900");
		System.out.println(p.height);
	}

}
