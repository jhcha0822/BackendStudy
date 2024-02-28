package flower;

// 패키지에 담겨진 클래스를 외부에서 사용하게 하려면 반드시 해당 클래스를 public으로 오픈해야 함

public class Rose {
	private String color = "Red"; // default 접근제한자 생략: 같은 패키지만 가능

	public String getColor(){
		return color;
	}

	public void setColor(String color){
		this.color = color;
	}
}