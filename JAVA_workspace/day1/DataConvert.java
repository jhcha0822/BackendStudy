class DataConvert{
	public static void main(String[] args){
		short s1=3;
		short s2=4;

		short sum = s1+s2;
		// ���� �߻�
		// ���� 32bit ��ǻ�Ϳ��� ���� ����� int�� �ٲ�� ����
		// (int ���� ���� byte�� short) ���� ���� �� int�� �ڵ� ����ȯ
		// 64bit ��ǻ�͵� ������

		// �̸� ���� ���ؼ��� ���� ����ȯ (cast) �̿�
		// short sum = (short)s1 + (short)s2;
	}
	
}