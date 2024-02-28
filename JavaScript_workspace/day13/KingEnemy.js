/*
   부모로 물려받는 기능이 현재 상황에 맞지 않을 경우 업그레이드할 필요가 있다.
   즉, 부모가 물려준 메서드를 동일한 이름으로 재정의하는 기법을

    오버라이딩
    메서드명을 부모와 자식 둘 다 보유하고 있으므로,
    호출시엔 자식의 메서드를 최우선하여 호출
*/

class KingEnemy extends Enemy{
    constructor(container, src, x, y, width, height, velX, velY, r, velR){
        super(container, src, x, y, width, height, velX, velY);

        this.r = r; // rotate를 위한 값
        this.velR = velR; // 각속도

        // style
        this.img.style.transform = `rotate(${this.r}deg)`
    }

    // 보스는 움직임이 다르므로 부모의 메서드와 동일한 이름으로 메서드 재정의
    // 오버라이드 시엔 메서드명과 매개변수 모두 동일하게 정의
    move(){
        // 물리량 변화
        this.r += this.velR;
        this.x += this.velX;
        this.y += this.velY;

        // rendering
        this.img.style.transform = `rotate(${this.r}deg)`;
        this.img.style.left = this.x+"px";
        this.img.style.top = this.y+"px";
    }
}