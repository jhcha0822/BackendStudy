class Human {
	int eyenum=2;
	int legs=2;
	public void eat(){
		System.out.println("살려고 먹어요");
	}
	public void think(){
		System.out.println("지성이 있어요");
	}

	public Human(int eyenum, int legs){
		this.eyenum = eyenum;
		this.legs = legs;
	}
}
