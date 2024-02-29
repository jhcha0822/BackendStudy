# JQuery

자바스크립트 라이브러리

코드의 간략화를 위해 사용

업무효율성 및 협업을 위해 사용한다.

```js
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
```

### 문법

JQuery는 문법 구문(syntax)이 아래와 같이 단순한 구조임

`$(누구를).어떻게()`

```js
$(document).ready(function(){
    alert("JQuery");
});    
// addEventListener("load", function(){}); 와 동일
    
// 줄여쓰는 방법
$(function(){});