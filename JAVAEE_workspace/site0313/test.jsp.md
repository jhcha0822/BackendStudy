jsp 파일 정의하는 법: 확장자만 `.jsp`이면 된다.

jsp를 작성할 수 있는 영역
1) 지시영역: `<%@여기%>` 현재 페이지의 종류(이미지, 음원, 텍스트, html, xml, json 등등) 및 인코딩 지정, `import` 등
            현재 페이지의 설정 정보 관련 코드를 작성
2) 선언부: `<%!여기%>` 현재 JSP의 멤버변수와 멤버메서드를 작성할 수 있는 영역
3) 스크립틀릿(Scriptlet) 영역: `<%여기%>` `service()` 메서드의 영역, 로직을 작성함
4) 표현식: `<%=출력데이터%>` `out.print();`를 줄여쓴 표현

확장자가 jsp인 경우, 이 안에 html이 섞여 있다면 jsp는 백엔드 기술이기에 html은 무시하고 jsp 영역만 수행한다.