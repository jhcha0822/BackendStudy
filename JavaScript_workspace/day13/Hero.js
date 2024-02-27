class Hero extends GameObject{
    constructor(container, src, x, y, width, height, velX, velY){
        super(container, src, x, y, width, height, velX, velY); // 상속관계에 있는 자식의 생성자가 호출될 때에는 반드시 부모의 초기화 선행
    }
}