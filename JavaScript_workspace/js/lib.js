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