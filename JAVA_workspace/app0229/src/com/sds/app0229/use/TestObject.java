// �ڹٿ����� �����ڰ� �ƹ��� ��Ӱ��踦 ������� �ʾƵ�
// �⺻������ ������ ��ӵǴ� �ֻ��� ��ü�� Object�� �����Ѵ�

package com.sds.app0229.use;

// Object�� import���� �ʾƵ� java.lang�� �⺻ import�Ǿ� �־� ���� X

class TestObject /* extends Object*/ { /* TestObject is a Object */
	public static void main(String[] args) {
		
		// �Ʒ��� 3 ���۷��� ���� ��� �ڽ��� TestObject�� �ν��Ͻ� �ּҸ� ����Ų��
		// ��Ӱ��迡�� �θ��� �ν��Ͻ� �ּҰ��� �޸𸮿� �ö� ����� ���⿡ �ּҸ� ������ �� ����

		// TestObject to = new TestObject(); // A ����
		Object obj = new TestObject();    // B ����
		// System.out.println("Hello World!");

		TestObject re = (TestObject)obj; // C

		// A: �ڽ� �ν��Ͻ� + �θ� ���
		// B: �ڽ��� �ν��Ͻ� �� �θ� ��� (����. �ڽ��� �������̵��� �޼��尡 ������ �� �޼��� ȣ��)
		// C: 
	}
}
