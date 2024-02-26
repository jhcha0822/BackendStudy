public class Hero {
	// (가)~(아)까지 조건에 맞는 메서드를 완성하고 호출 코드도 작성해보세요
	int hp=10;
	boolean fly=false;
	String name="메가맨";
	Bullet bullet;
	
	public void setHp(int hp) { 
             // (가) 멤버변수 hp 값을 변경하고 싶다 
			 this.hp = hp;
	}

	public void setFly(boolean fly) {
             // (나)멤버변수 fly 값을 변경하고 싶다
			 this.fly = fly;
	}

	public void setName(String name) {
             // (다)멤버변수 name 값을 변경하고 싶다		
			 this.name = name;
	}

	public void fire(Bullet bullet){
             // (라)멤버변수 bullet 에 총알을 대입하고 싶다
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
