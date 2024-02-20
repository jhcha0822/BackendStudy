/*
    클래스 정의 시, 반드시 클래스명은 대문자로 시작하도록 한다.
    합성어인 경우, 뒷단어의 첫 철자도 대문자로 시작해야 한다.
    ex) MyServer
    CamelCase 낙타등 기법
*/

// 대포 생성에 이용되는 클래스
// 모든 클래스는 언어를 구분하지 않고 클래스 내 속성과 메소드로 이루어짐
// 속성(변수), 메소드(함수)
class Missile{
    // 생성자 메소드란 객체를 생성하는 시점에 관여하는 초기화 코드
    // 주로 객체가 어떤 스타일로 태어날지를 결정함
    constructor(src, x, y, velX, velY, width, height){
        this.img = document.createElement("img");
        this.src = src;
        this.x = x; // 생성 시 x축 좌표
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.width = width+"%";
        this.height = height+"%";

        // 멤버 변수들을 이용하여 스타일을 지정
        this.img.style.position = "absolute";
        this.img.src = this.src;
        this.img.style.left = this.x;
        this.img.style.top = this.y;
        this.img.style.width = this.width;
        this.img.style.height = this.height;

        // body 태그: 부모에 부착
        document.body.appendChild(this.img);
    }

    // 움직임을 정의하는 코드(함수)를 메서드라 부름
    move(){
        this.x += this.velX;
        this.y += this.velY;

        this.img.style.left = this.x+"px";
        this.img.style.top = this.y+"px";
    }
}