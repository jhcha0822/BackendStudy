
package com.sds.app0229.car;

public class Car {
	// ��ü�ڷ����� �ڷ����̹Ƿ�, ��������� ���� ����
	// ��������� ��ü�ڷ����̸� has a �����̴�
	public Wheel[] wheel;
	public Handle handle;
	public Door[] door;
	int price = 10000000; // ��ü�� �ƴϱ⿡ has a ���谡 �ƴ�

	// ��ü�� ������ Ŭ������ �ν��Ͻ��� �ø� ���� �ʱ�ȭ �۾��� �Ϲ� �ڷ����� ���� ����
	// ���� ������ ������ Ȱ���� �ʿ���
	public Car(){
		wheel = new Wheel[4]; // �迭 ����, ũ�� ���
		wheel[0] = new Wheel();
		wheel[1] = new Wheel();
		wheel[2] = new Wheel();
		wheel[3] = new Wheel();

		handle = new Handle();

		door = new Door[4];
		door[0] = new Door();
		door[1] = new Door();
		door[2] = new Door();
		door[3] = new Door();
	}
}
