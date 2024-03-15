<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>

<%!
    // jsp 페이지의 멤버영역
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String user = "seshop";
    String pass = "1234";
%>
<%
    // 오라클 연동을 위한 목적이기에 디자인이 필요없음
    // 따라서 html영역은 포함하지 않음
    // 클라이언트에서 전송한 제목, 작성자, 내용을 네트워크로 전송받아 오라클에 대신 넣어주자

    // 클라이언트가 요청을 시도할 때, 서버측에서는 해당 요청을 처리하기 위해 무조건 내장객체 2개 생성
    // 1) request: 클라이언트의 요청 정보가 들어있음 (전송된 데이터를 추출할 때 유용)
    // 2) response: 클라이언트에게 응답할 정보를 가진 객체 (추후 응답시: html 재구성 시)

    // 클라이언트가 전송한 데이터 받기 (파라미터(변수값) 받기)
    // 한글을 네트워크상으로 날리면 따로 인코딩 처리가 필요함
    // 문서가 utf-8일때 문서 내의 한글이 안깨지는것 뿐.

    // request 객체를 이용하여 전송된 파라미터에 대한 인코딩 처리
    request.setCharacterEncoding("utf-8"); // 파라미터 받기 전 수행

    String title = request.getParameter("title");
    String writer = request.getParameter("writer");
    String content = request.getParameter("content");

    // 전송되어 온 파라미터를 이용하여 SQL 준비한 뒤 쿼리 실행
    // WEB-INF는 외부에서 접근이 불가능
    // 보안 처리된 디렉토리이므로 .class나 .jar 등 위치 (WEB-INF/lib/)
    // Tomcat은 server.xml, 새 클래스나 jar 추가 시 재가동 필요

    Class.forName("oracle.jdbc.driver.OracleDriver");
    out.print("드라이버 로드 성공");

    // 오라클 접속
    Connection con = null;
    PreparedStatement pstmt = null;
    con = DriverManager.getConnection(url, user, pass);
    if(con == null)
        out.print("접속 실패");
    else {
        out.print("접속 성공");
        String sql = "insert into notice(notice_idx, title, writer, content)";
        sql += " values(seq_notice.nextval, '"+title+"' , '"+writer+"' ,'"+content+"')";

        pstmt = con.prepareStatement(sql); // 쿼리수행 객체

        // 쿼리실행 DML executeUpdate() 수행 후 insert 이기에 성공 시 1 반환
        int result = pstmt.executeUpdate();
        if(result<1) {
            out.print("등록 실패");

        }
        else {
            out.print("등록 성공");
            // 리스트 보여주기
            out.print("<script>");
            out.print("alert('등록성공');");
            out.print("location.href='/notice/list.jsp';");
            out.print("</script>");
        }
    }

    if(pstmt != null)
        pstmt.close();
    if(con != null)
        con.close();
%>