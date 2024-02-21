class Hero{
    constructor(container, src, x, y, width, height, velX, velY){
        this.container = container; // 어디에 붙일지
        this.img = document.createElement("img"); // 어떤 형식으로 만들지: img
        this.src = src;
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;

        // style
        this.img.src = this.src;
        this.img.style.position = "absolute";
        this.img.style.left = this.x+"px";
        this.img.style.top = this.y+"px";
        this.img.style.width = this.width+"px";
        this.img.style.height = this.height+"px";

        this.container.appendChild(this.img)
    }

    move(){
        // Update
        this.x += this.velX;
        this.y += this.velY;


        // Rendering
        this.img.style.left = this.x+"px";
        this.img.style.top = this.y+"px";
    }
}