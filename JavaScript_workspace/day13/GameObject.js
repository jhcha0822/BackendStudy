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

    move(){
        // 물리량
        this.x += this.velX;
        this.y += this.velY;

        // 그래픽
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";
    }
}