<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%
    int gallery_idx = Integer.parseInt(request.getParameter("gallery_idx"));
    String filename = request.getParameter("filename");

    // 파일 삭제
    String savePath = "D:/MULTICAMPUS/JAVAEE_workspace/site0318/data";
    File old = new File(savePath+filename);
    boolean flag = old.delete(); // 파일 삭제

    // DB 삭제
    // Oracle 연동
    // 1) 드라이버 로드
    Class.forName("oracle.jdbc.driver.OracleDriver");

    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String user = "seshop";
    String pw = "1234";

    Connection con = null;
    PreparedStatement pstmt = null;
    con = DriverManager.getConnection(url, user, pw);
    if(con != null) {
        String sql = "delete gallery where gallery_idx="+gallery_idx;

        pstmt = con.prepareStatement(sql); // 쿼리문 준비객체

        // 쿼리 수행 후 영향 받은 레코드의 수 반환
        int result = pstmt.executeUpdate(); // DML
        if(result>0) {
            out.print("<script>");
            out.print("alert('삭제성공');");
            out.print("location.href='/gallery/list.jsp';"); // 리스트 요청
            out.print("</script>");
        }
        else {
            out.print("<script>");
            out.print("alert('삭제실패');");
            out.print("history.back();");
            out.print("</script>");
        }
    }
    if(pstmt != null)
        pstmt.close();
    if(con != null)
        con.close();
%>