
package com.sds.app0229.use;

import com.sds.app0229.bird.Duck;
import com.sds.app0229.bird.Bird;

class UseDuck {
	public static void main(String[] args) {		
		// ���� �ν��Ͻ� ����
		Duck d = new Duck();

		// �θ��� name ���
		System.out.println(d.name);

		// �θ��� �޼��� ���
		d.eat();

		// ������ ����� �ҷ��� ������ ���ٰ� ��������
		Bird bird = new Duck();

		System.out.println(bird.age);
	}
}
