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
    String notice_idx = request.getParameter("notice_idx");
    String sql = "delete notice where notice_idx="+notice_idx;

    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con = null;
    PreparedStatement pstmt = null;
    con = DriverManager.getConnection(url, user, pass);
    if(con != null) {
        pstmt = con.prepareStatement(sql); // 쿼리수행 객체
        int result = pstmt.executeUpdate();
        if(0 < result)
            out.print("<script>");
            out.print("location.href='/notice/list.jsp';");
            out.print("</script>");
    }
    if(pstmt != null)
        pstmt.close();
    if(con != null)
        con.close();
%>