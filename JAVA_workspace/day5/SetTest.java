// Collection Framework���� �����ϴ� ��ü ��, 
// ���� ���� ��ü���� ������ �ٷ�� Set

// Set�� Ư¡
// 1. ���� ���� ����
// 2. �ߺ� ��� X (���)
// 3. ���� ��ü���� ���� �� �ִ�

import java.util.HashSet;

class SetTest {
	public static void main(String[] args) {
		HashSet set = new HashSet();

		set.add("BMW");
		set.add("AUDI");
		set.add("Benz");

		// ��� ��ҵ��� �ݺ������� ��� �����ұ�?
		// ������ ���⿡ �Ұ���

		// ���� ���� ���� �������� ��������
		// 1. Enumeration (enum)
		// 2. Iterator

		Iterator it = set.iterator();

		System.out.println(it);
	}
}
