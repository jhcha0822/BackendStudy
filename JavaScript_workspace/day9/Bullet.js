class Bullet{
    constructor(container, x, y, width, height, velX, velY){
        this.container = container;
        this.div = document.createElement("div");
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;

        // style
        this.div.style.borderRadius = 50+"%";
        this.div.style.background = "red";

        this.div.style.position = "absolute";
        this.div.style.left = this.x+"px";
        this.div.style.top = this.y+"px";
        this.div.style.width = this.width+"px";
        this.div.style.height = this.height+"px";
        this.div.style.filter = "blur(1px)";
        
        this.container.appendChild(this.div);
    }

    move(){
        // Update
        this.x += this.velX;
        this.y += this.velY;

        // 현재 총알의 인스턴스와 게임에 존재하는 모든 적군과의 충돌 검사 후,
        // 충돌(1)이라면 화면 및 배열에서 총알과 적군 모두 제거
        for(let i=0; i<enemyArray.length; i++) {
            if(collisionCheck(this, enemyArray[i])){
                wrapper.removeChild(this.div); // 화면에서 제거
                bulletArray.splice(bulletArray.indexOf(this), 1); // 배열에서 제거
                
                // enemyArray는 div가 아니라 img임
                wrapper.removeChild(enemyArray[i].img); // 화면에서 제거
                enemyArray.splice(i, 1); // 배열에서 제거

                score+=enemyArray[i].score;
            }
        }

        // 화면의 한계점을 넘어가면, 총알 제거(화면에서 + 배열에서)
        if(this.x >= 1280) {
            wrapper.removeChild(this.div); // 화면에서 제거
            bulletArray.splice(bulletArray.indexOf(this), 1); // 배열에서 제거
        }

        // Rendering
        this.div.style.left = this.x+"px";
        this.div.style.top = this.y+"px";
    }
}