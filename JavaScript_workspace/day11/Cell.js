// 달력 Cell을 정의

class Cell{
    constructor(container, x, y, width, height, content){ // , icon){
        this.container = container;
        this.div = document.createElement("div");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.content = content; // Cell에 채워질 내용
        /*
        this.img = document.createElement("img");
        this.img.src = icon; // 이미지 경로
        this.img.width = 30+"px";
        
        if(icon.length > 0) // 이미지 경로가 존재한다면
            this.div.appendChild(this.img); // 붙이기
        */

        // Style
        this.div.style.border = "1px solid gray";
        this.div.style.position = "absolute";
        this.div.style.left = this.x+"px";
        this.div.style.top = this.y+"px";

        this.div.style.width = this.width+"px";
        this.div.style.height = this.height+"px";

        // Cell에 표시될 텍스트 정보
        this.div.innerText = this.content;
        this.container.appendChild(this.div);
    }

    setContent(content){
        this.content = content;
        this.div.innerText = this.content;
    }
}