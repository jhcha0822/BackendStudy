<%@ page contentType="text/html;charset=utf-8" %>
<%!
    // 멤버 영역(멤버변수 및 메서드 정의 영역)
    String title = "jsp test";
    public String getMsg() {
        return title;
    }
%>
<%
    for(int i=1; i<=10; i++) {
        out.print(getMsg()+"<br>"); // 클라이언트 웹브라우저에 지정한 문자열 출력
                             // 이때 문자열이 <태그>인 경우 클라이언트인 웹브라우저는 태그로 해석함
    }
%>
<%="표현식에 의한 출력"%>