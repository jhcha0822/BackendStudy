class Ball{
    constructor(container, bg, x, y, width, height, velX, velY, text){
        this.container=container;
        this.div=document.createElement("div");
        this.bg=bg;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.velX=velX;
        this.velY=velY;
        this.text=text; // 공의 좌표 출력용

        // style
        this.div.style.background=this.bg;
        this.div.style.borderRadius=50+"%";
        
        this.div.style.position="absolute";
        this.div.style.left=this.x+"px";
        this.div.style.top=this.y+"px";
        
        this.div.style.width=this.width+"px";
        this.div.style.height=this.height+"px";

        //부모 요소에 부착 
        this.container.appendChild(this.div);
    }

    move(){
        this.x += this.velX;
        this.y += this.velY;

        if(this.x<=0 || (this.x+this.width)>=900)
            this.velX = this.velX*(-1);
        if(this.y<=0 || (this.y+this.height)>=900)
            this.velY = this.velY*(-1);

        let str=this.x+"\n";
        str = str+this.y;

        this.div.style.left = this.x+"px";
        this.div.style.top = this.y+"px";

        this.div.innerText=str;
    }
}