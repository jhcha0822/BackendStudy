// 게임에 등장하는 모든 객체들의 최상위 객체
class GameObject{
    constructor(container, src, x, y, width, height, velX, velY){
        this.container = container;
        this.img = document.createElement("img");
        this.src = src;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;

        this.img.src = this.src;
        
        // style
        this.img.style.position = "absolute";
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";

        this.img.style.width = this.width + "px";
        this.img.style.height = this.height + "px";

        // 부착
        this.container.appendChild(this.img);
    }

    // JS는 완벽한 OOP가 아니기 때문에, 메서드의 몸체를 제거하는
    // 즉, 불완전한 메서드로 만드는 기법을 지원하지 않는다.

    // 내용을 작성하지 말아야 하는 이유: 미래의 어떤 자식이 정의될 지 최상위 객체 입장에서는 예측 불가
    // 구현강제: 자식은 로직을 맘대로 작성하나 메서드명은 정해줌
    // 모든 자식이 로직은 틀리지만 동일한 자료형으로 여러 객체로 동작할 수 있는 다형성이 구현됨
    
    // 추상 클래스
    move(){}
    /*    
        // 물리량
        this.x += this.velX;
        this.y += this.velY;

        // 그래픽
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";
    }
    */
}