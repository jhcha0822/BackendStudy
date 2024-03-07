package stream.copy;

/*
������ ����
- Stream�̶�?
  ���� ����, ���ٱ� �� �帣�� ���� �ǹ�
  ��ǻ�Ϳ����� ���� �帧�� �ƴ� �������� �帧�� �ǹ�
- ���⼺�� ���� ���� (IO)
  Input: �������� ���α׷����� �����Ͱ� ����
  Output: �������� ���α׷����� �����Ͱ� ����
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.FileNotFoundException; // ���ܵ� import �ؾ���
import java.io.IOException;

public class ImageCopy {
	FileInputStream fis; // ������ ������� �� �Է� ��Ʈ�� ��ü
						 // �� Ŭ������ �������� �ڹ� ���α׷����� ������ �̷�� �ִ� ����Ʈ �����͵��� �о� ���� �� ����
	FileOutputStream fos; // ������ ������� �� ��� ��Ʈ�� ��ü
						  // �� Ŭ������ �̿��ϸ� �������� �ڹ� ���α׷����� ������ ���Ϸ� ����Ʈ �����͸� ����� �� ����(����)

	String filename = "D:/MULTICAMPUS/JAVA_workspace/app0307/res/hello.txt";
	// "D:/MULTICAMPUS/JAVA_workspace/app0307/res/chicken.png"; // ��������\�� �����츸 ���. �ڹٴ� �߸����� ���� ������/ �Ǵ� �������� 2��\\ �̿�

	String destname = "/MULTICAMPUS/JAVA_workspace/app0307/res/hello_copy.txt";
	
	public ImageCopy() {
		// ���������� ������ ���� �Ͽ� ������ ������ ������ �����ϴ� ���� �ƴϴ�
		// ��, ���α׷��� ���� ������ �����ϴ� ������ �����Ѵ�: ����(Exception)
		// �����ڰ� ���ܻ����� ������ ������ ������ �Ұ����ϴ�

		// ���α׷��� ���� ������ �����ϴ� ���ο��� ũ�� 2���� ����
		// 1) ����: ����� �����ڰ� ������ �� ���� ��� ex) ����, �ϵ����, ��Ʈ��ũ ���� �� --> ���α׷��� ���� ����� �ƴ�
		// 2) ����: ���α׷��Ӱ� ������ �� ������, ���α׷��� �� ������ �帧�� ���� �帧���� �������Ⱑ ������
		
		// ������ ������ ����Ǵ� �ڵ带 try���� ���ΰ�
		// ����� �� ��Ȳ�� �߻��ϸ� ���α׷��� ������ ������ ��ġ���� �ʰ�, catch������ ����θ� ���Խ���
		// �˸��� ó���� �� �� �ֵ��� �����ϴ� ��� [����ó��]
		// �ߴ��ϴٰ� �ǴܵǴ� ���� ������ SUN�� ���صξ��⿡ �����ڴ� ������ �� �����Ϸ��� �ȳ��� ���� ������ ����ó�� ����

		// try-catch���� �ۼ����� ������ �������� �Ұ�����

		int data = -1;

		try{ // �� ���� ������ ����Ǵ� �ڵ带 try�� ���γ���
			fis = new FileInputStream(filename);
			fos = new FileOutputStream(destname);
			System.out.println("���Ͽ� ���� �Է½�Ʈ�� ����");

			System.out.println("���� �б� ����");
			while(true) {
				data = fis.read();
				if(data == -1) // EOF
					break;
				// System.out.print((char)data);
				fos.write(data);
			}
			// try���� ��Ʈ�� ������ �־�θ� catch�� �������� �� ��Ʈ���� ������ ����
			
			/*
			int data = fis.read(); // 1byte �б�, ���� �߻�
			System.out.print("���� �б� ����: "+(char)data);
			data = fis.read(); // read�� ȣ���� �� ���� ���� �����ͷ� �����͸� �ű�
			System.out.print((char)data);
			*/

		} catch(FileNotFoundException e) { // �Ұ�ȣ �ȿ��� ������ ���� ���� ���ܸ� ǥ���� ��ü ����. ����� ��Ȳ�� �߻��ϸ� JVM�� �ش� ���ܿ� ���� ������ ��ü�ν��Ͻ��� �����Ͽ� catch ������ �Ű������� ����
			// ������ �Ұ��������� ����ڿ��� �ŷڸ� ���� ���� �ȳ� �޽��� �ۼ� (������ �ƴ�)
			System.out.println("������ ��θ� Ȯ�����ּ���.");
		} catch(IOException e) { // ���� catch��, �аų� �� �� �߻��ϴ� ���� ����
			System.out.println("������ �б� �Ǵ� ���� ����");
		} finally { // ����ߴ� ��Ʈ���� �ݱ� (�޸𸮿��� ����)
					// �ݳ��� �ش� ��Ʈ���� �޸𸮿� �ö��������(not null) ����
				//sun������ close���� ����ó���� ����
				try{
					if(fos != null)
						fos.close();
				} catch(IOException e) {
					e.printStackTrace(); // �ֿܼ� ��µǴ� �޽���, �Ϲ� ������ ���� ���� �ƴ�. �ַ� �����Ͼ� �Ǵ� �ý��۰�����
				}
			if(fis != null) {
				try{
					fis.close(); // fis�� �������� ���, null�� �����ϰ� ��: NullPointerException
								 // ��ü�� �������� ���� �� �� �޼��忡 ���� ����� �ԷµǾ��� ��
				} catch(IOException e) {
					e.printStackTrace();
					System.out.println("������ ��� �˼��մϴ�.");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new ImageCopy();
	}
}
