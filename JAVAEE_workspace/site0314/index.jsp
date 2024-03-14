<!-- 
    JSP를 작성할 수 있는 코드 영역은 총 4개
    1) 지시영역: %@ % 
        현재 JSP에 대한 마임타입, 인코딩, 임포트 등의 설정
    2) 선언부: %! % 
        현재 페이지의 멤버변수와 멤버메서드 정의
    3) 스크립틀릿 영역: % % 
        service()메서드 안쪽의 영역; 자바 코드 및 로직 정의
    4) 표현식 영역: %= %
        out.print();를 줄여쓴 영역으로 문자, 숫자, 논리값인 데이터를 클라이언트 브라우저로 출력
 -->

<!-- 
    내장 객체(built-in-object): 웹 어플리케이션 개발 시 필수적이고 중요한 기능을 지원하는
    [인스턴스가 이미 서버에 내장되어진 객체]. 각 인스턴스에 부여된 고유한 이름 존재(변경불가)
    종류:
        ★ request: 요청을 받는 객체
        ★ response: 클라이언트에게 응답정보를 구성하는 객체
        - out: 클라이언트에게 전송할 문자열을 담는 스트림
             response 객체가 보유한 출력스트림에 문자열을 모음
        - page: 현재 jsp 페이지의 인스턴스를 표현한 객체
        ★ session: 클라이언트의 세션을 담는 객체
        ★ application: 현재 어플리케이션 정보를 담는 객체
        - pageContext: (서블릿과 함께 공부)
        - config: (서블릿과 함께 공부)
        - exception: jsp에서의 예외 정보를 담는 객체
-->
<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <!-- HTML의 폼태그는 입력양식 태그들(input, checkbox 등)을 전송해주는 역할을 수행
        즉, 문서내의 특정 영역을 양식으로 감싸고, 그 영역을 전송 가능 -->
    <form action="/receive.jsp">
    <!-- form action="http://localhost:8888/receive.jsp" 과 동일 -->
        <!-- id와 name은 하나의 문서내에서 요소들을 구분하기 위한 용도로써만 사용해왔지만
            서버 연동 프로그래밍에서 name은 전송 시 변수 역할을 수행한다.
            이때 이 전송용 변수를 가리켜 전송 파라미터(parameter)라 한다. -->
        <input type="text" placeholder="아이디 입력" name="id">
        <input type="text" placeholder="비밀번호 입력" name="pw">
        <!-- 아래의 버튼은 고전적 버튼이 아닌, HTML5의 버튼이므로 디폴트 속성 submit 보유
            폼 태그 안에 위치시킬 경우 현재 폼의 입력양식을 전송한다.
            단, 이 경우 전송주소를 명시하지 않았다면 새로고침만 일어남 
            1) 보낼 주소 명시
            2) 자동 전송 막기
            3) 폼 태그 밖으로 꺼내기 -->
        <button>서버에 전송</button>
    </form>
</body>
</html>