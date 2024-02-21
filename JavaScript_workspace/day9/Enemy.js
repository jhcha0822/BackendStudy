class Enemy{
    constructor(container, src, direct, score, x, y, width, height, velX, velY){
        this.container = container;
        this.img = document.createElement("img");
        this.src = src;
        this.direct = direct; // 1: 우 -1: 좌
        this.score = score; // 해당 적군의 점수

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;

        // style
        this.img.src = this.src;
        this.img.style.transform = `scaleX(${this.direct})`;

        this.img.style.position = "absolute";
        this.img.style.left = this.x+"px";
        this.img.style.top = this.y+"px";

        this.img.style.width = this.width+"px";
        this.img.style.height = this.height+"px";

        this.container.appendChild(this.img);
    }

    move(){
        this.x += this.velX;
        this.y += this.velY;

        // rendering
        this.img.style.left = this.x+"px";
        this.img.style.top = this.y+"px";
    }
}