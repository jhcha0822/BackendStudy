public class Hero {
	// (��)~(��)���� ���ǿ� �´� �޼��带 �ϼ��ϰ� ȣ�� �ڵ嵵 �ۼ��غ�����
	int hp=10;
	boolean fly=false;
	String name="�ް���";
	Bullet bullet;
	
	public void setHp(int hp) { 
             // (��) ������� hp ���� �����ϰ� �ʹ� 
			 this.hp = hp;
	}

	public void setFly(boolean fly) {
             // (��)������� fly ���� �����ϰ� �ʹ�
			 this.fly = fly;
	}

	public void setName(String name) {
             // (��)������� name ���� �����ϰ� �ʹ�		
			 this.name = name;
	}

	public void fire(Bullet bullet){
             // (��)������� bullet �� �Ѿ��� �����ϰ� �ʹ�
			 this.bullet = bullet;
	}

	public static void main(String[] args) {
		Hero hero = new Hero();
		hero.setHp(50);
		hero.setFly(true);
		hero.setName("Batman");

		Bullet bullet = new Bullet();
		hero.fire(new Bullet());		

	}	
}
