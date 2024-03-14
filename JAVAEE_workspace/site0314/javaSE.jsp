<%@ page contentType="text/html;charset=utf-8"%>
<%
    // 자바SE에서 URLConnection에 의한 요청을 처리하기 위함
    // 웹 서버에서는 클라이언트가 자바SE건 웹브라우저건 관심이 없음
    // 즉 고양이는 http 프로토콜로 말을 걸어올 때 무조건 응답을 하지
    // 대상은 관심이 없음
    System.out.println("클라이언트 접속 감지"); // 서버측 콘솔에 출력
    out.print("OK"); // 미래의 응답정보 객체 response에 클라이언트에게 보내질 문자열을 채우는 과정
%>