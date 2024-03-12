<%@ page contentType="text/html;charset=utf-8"%>

<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>

<%
    // jsp는 java의 문법을 따르므로, 자바의 문법으로 작성
    // oracle 접속 정보
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String user = "wendy";
    String pw = "1234";

    Connection con = null; // 접속 정보를 가진 인터페이스 객체
    PreparedStatement pstmt = null; // 쿼리 수행 객체
    ResultSet rs = null; //결과 집합인 레코드를 담는 객체 

    // jsp의 코드 영역은 서버에서 실행됨: 백엔드
    // 원본 그대로를 응답해주지 않고 실행 결과를 클라이언트(웹브라우저)가 이해할 수 있는 html로 구성해 전송

    // Oracle 접속해보기
    // javaEE는 javaSE를 포함하기에 SE코드가 가능함
    // 1) 드라이버 로드
    // 드라이버는 SUN 사에서 정의한 대로, WEB-INF 내 lib에 *.jar파일 넣게끔 정의됨
    Class.forName("oracle.jdbc.driver.OracleDriver");
    out.print("드라이버 로드 성공<br>");

    // 2) 접속
    con = DriverManager.getConnection(url, user, pw);
    if(con == null)
        out.print("접속 실패<br>");
    else {
        out.print("접속 성공<br>");

        // 3) 쿼리문 날리기
        String sql = "select * from car";
        pstmt = con.prepareStatement(sql); // 쿼리 수행 객체

        // 쿼리 실행
        rs = pstmt.executeQuery();  // select문의 결과를 받아옴

        // rs는 최초에 아무런 레코드도 가리키고 있지 않음
        // 따라서 커서를 적절히 배치해 해당 위치의 레코드를 가져와야 함

        out.print("<table width=\"100%\" border=\"1px\">");
        while(rs.next()) {
            out.print("<tr>");
            out.print("<td>"+rs.getInt("car_idx")+"</td>");
            out.print("<td>"+rs.getString("name")+"</td>");
            out.print("<td>"+rs.getInt("price")+"</td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }
    // 4) 자원 해제
    if(rs != null)
        rs.close();
    if(pstmt != null)
        pstmt.close();
    if(con != null)
        con.close();
    
%>