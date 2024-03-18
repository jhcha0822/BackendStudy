<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>

<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%
    // 클라이언트가 전송한 복합형 데이터(multipart)를 넘겨받아 수정을 처리
    // 이미지가 넘어온 경우는 기존 파일 삭제 (이미지 수정은 불가능, 삭제 후 재등록)
    // 이미지가 넘어오지 않았다면, 즉 재 업로드가 되지 않았다면 그대로 유지 (DB만 업데이트)
    // https://javadoc.io/doc/commons-fileupload/commons-fileupload/latest/index.html

    DiskFileItemFactory factory = new DiskFileItemFactory();
    int maxSize = 1*1024*1024;
    String savePath = "D:/MULTICAMPUS/JAVAEE_workspace/site0318/data/";
    factory.setSizeThreshold(maxSize);
    factory.setRepository(new File(savePath)); //저장 경로
    
    // 업로드 처리 객체 생성
   ServletFileUpload upload = new ServletFileUpload(factory); // 설정 적용

    // 업로드된 데이터 분석(parse)
    List<FileItem> itemList = upload.parseRequest(request); // 내장 객체를 인수로 받아 분석

    // 리스트에 채워진 각 FileItem들을 꺼내 정보를 사용
    String title = null;
    String writer = null;
    String content = null;
    String filename = null;
    int gallery_idx = 0;

    for(FileItem item : itemList) {
        if(item.isFormField()) {// 일반 텍스트 데이터
            if(item.getFieldName().equals("title"))
                title = item.getString("utf-8");
            else if(item.getFieldName().equals("writer"))
                writer = item.getString("utf-8");
            else if(item.getFieldName().equals("content"))
                content = item.getString("utf-8");
            else if(item.getFieldName().equals("filename"))
                filename = item.getString("utf-8");
            else if(item.getFieldName().equals("gallery_idx"))
                gallery_idx = Integer.parseInt(item.getString("utf-8"));
        }
        else {// 바이너리 파일 데이터
            // 새 파일이 업로드한 경우
            // 기존 서버에 있는 파일의 물리적 삭제 및 filename 변수도 새롭게 생성된 파일로 교체
            // out.print(item.getName().length()); 파일 수정 시 길이가 1 이상
            if(item.getName().length()>0) {
                // 서버에 있는 기존 파일 삭제
                File old = new File(savePath+filename);
                boolean flag = old.delete(); // 파일 삭제
                // 새 파일명 생성, filename 변수에 새로 생성된 이름으로 대체
                long time = System.currentTimeMillis();
                String str = item.getName();
                String ext = str.substring(str.lastIndexOf(".")+1, str.length());
                filename = time+"."+ext;
                // 파일 저장
                item.write(new File(savePath+filename));
            }
        }
    }

    // 수정 쿼리
    // String sql = "update gallery set title=?, writer=?, content=?, filename=?";
    // sql += " where gallery_idx="

    String sql = "update gallery set title='"+title+"', writer='"+writer+"', content='"+content+"', filename='"+filename+"'";
    sql += " where gallery_idx="+gallery_idx;

    Class.forName("oracle.jdbc.driver.OracleDriver");
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String user = "seshop";
    String pw = "1234";

    Connection con = null;
    PreparedStatement pstmt = null;

    con = DriverManager.getConnection(url, user, pw);
    pstmt = con.prepareStatement(sql);
    int result = pstmt.executeUpdate(); // DML
    if(result>0) {
        out.print("<script>");
        out.print("alert('수정 성공');");
        out.print("location.href='/gallery/content.jsp?gallery_idx="+gallery_idx+"';");
        out.print("</script>");
    }
    else {
        out.print("<script>");
        out.print("alert('수정 실패');");
        out.print("history.back();");
        out.print("</script>");
    }
    if(pstmt != null)
        pstmt.close();
    if(con != null)
        con.close();
%>