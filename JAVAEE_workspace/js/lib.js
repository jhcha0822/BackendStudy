// 앞으로 재사용가능성이 높으며, 유용하거나, 복잡한 로직은 함수로 정의해두고
// 이 파일 안에 모아두기

// 구구단
function dan(n) {
    for(let i=1; i<10; i++)
        document.write(`${n}*${i} = ${n*i};<br>`);
    document.write("<br>");
}

// 0~max 사이의 랜덤값 반환하기
function getRandom(max) {
    return parseInt((max+1)*Math.random()); // 0.xxxxxxxxxx 1보다 작은 실수가 난수로 반환
}

// 
function getRandomByRange(min, max) {
    return min + getRandom(max-min); // 0.xxxxxxxxxx 1보다 작은 실수가 난수로 반환
}

function collisionCheck(box1, box2){
    //좌측상단 모서리(북서)1사분면
    let side1 = ((box1.x+box1.width) >= box2.x) && ((box1.y+box1.height) >= box2.y);
    //우측상단 모서리 (북동) 2사분면
    let side2 = (box1.x <= (box2.x+box2.width)) && ((box1.y+box1.height) >= box2.y);
    //우측하단 모서리(동남) 3사분면 
    let side3 = (box1.x <= (box2.x+box2.width)) && (box1.y <=(box2.y+box2.height));
    //좌측하단 모서리(서남) 4사분면
    let side4 = ( (box1.x+box1.width) >= box2.x ) && (box1.y <=(box2.y+box2.height));
    return (side1 && side2 && side3 && side4)
}

// 문자열 내 특정 문자열을 다른 문자열로 대체해주는 함수
// filterLang(대상 문자열, 삭제할 문자열, 대체할 문자열)
function filterLang(inputArray, badArray, goodArray){
    let str = inputArray;
        for(let i=0; i<badArray.length; i++)
            str = str.replace(badArray[i], goodArray[i]);
    return str;
}