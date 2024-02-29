// 썸네일 및 큰 이미지에 사용될 사진 객체
class Photo{
    constructor(container, src, x, y, width, height){ // Vue와 React를 이용하면 쉽게 작성 가능
        this.container = container;
        this.img = document.createElement("img"); 
        this.src = src;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.img.src = this.src;

        // style
        this.img.style.position = "absolute";
        this.img.style.left = this.x+"px";
        this.img.style.top = this.y+"px";
        
        this.img.style.width = this.width+"px";
        this.img.style.height = this.height+"px";

        // 이미지에 테두리 추가 (boxmodel에 의하면 border, margin, padding은 크기에 영향을 준다)
        this.img.style.border = "2px solid black";
        this.img.style.boxSizing = "border-box"; // 경계를 안으로

        // 이미지에 클릭 이벤트 연결
        $(this.img).click(()=>{
            // 이벤트 처리 영역에서 this의 scope는 이벤트를 일으킨 객체를 의미
            // 이벤트 처리에 있어 바깥 영역의 요소를 선택할 수도 있어야 함
            // 자바스크립트 2015에서 정의된 => (화살표함수)를 사용하면 바깥쪽 상위 scope 영역에 접근 가능
            $("#pic").attr("src", this.src) // 문서의 속성은 attribute
            
            // 현재 클릭된 객체가 배열의 몇번째에 존재하는지 알 수 있다면
            // 같은 index를 갖는 = movie.marvel 배열로부터 정보를 획득할 수 있다
            let index = thumbArray.indexOf(this);
            let obj = movie.marvel[index]; // marvel 배열 내의 영화 json 객체 하나 추출

            let tag = "개봉일: "+obj.release_year+"<br>";
            tag += "부제목: "+obj.phase;
            $("#content").html(tag); // JQuery는 html 메서드 이용
        });

        this.container.appendChild(this.img);
    }
}