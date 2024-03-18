<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>

<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>

<%
    // 클라이언트가 전송한 텍스트와 바이너리가 합쳐진 상태 -> 기존의 request 객체만으로는 업로드 처리 불가
    // requset.getParameter()는 텍스트 정보만 처리 가능
    // Apache Fileupload 컴포넌트 이용

    // 업로드와 관련된 설정 정보를 담당하는 객체
    DiskFileItemFactory factory = new DiskFileItemFactory();

    // 용량 제한
    int maxSize = 1*1024*1024; //1M byte
    factory.setSizeThreshold(maxSize);

    // 저장 경로
    String savePath = "D:/MULTICAMPUS/JAVAEE_workspace/site0318/data";
    factory.setRepository(new File(savePath));

    // 업로드를 처리하는 객체인 ServeletFileUpload 객체를 생성하면서
    // 설정 정보 객체를 생성자의 매개변수로 전달
    ServletFileUpload upload = new ServletFileUpload(factory);

    // 업로드 처리(업로드 된 내용 분석)
    // request 내장객체를 매개변수로 넣으면 업로드된 요청을 분석하여
    // html의 모든 컴포넌트들을 FileItem이라는 객체로 받게 됨
    List<FileItem> itemList = upload.parseRequest(request);

    String title = null;
    String writer = null;
    String content = null;
    String filename = null;

    // 각 아이템에 담겨진 데이터 확인
    for(FileItem item : itemList) {
        if(item.isFormField()) { // 텍스트 박스라면
            out.print(item.getString("utf-8")+"<br>"); // text박스의 값을 추출하는 메서드
            if(item.getFieldName().equals("title"))
                title = item.getString("utf-8");
            else if(item.getFieldName().equals("writer"))
                writer = item.getString("utf-8");
            else if(item.getFieldName().equals("content"))
                content = item.getString("utf-8");
        }
        else {// 아니라면
            // 반복문 내에 섞여 있는 FileItem 중, 바이너리 파일을 골라내어 서버에 저장처리
             out.print("파일명은 "+item.getName()+"<br>"); //파일의 이름을 추출
             long time = System.currentTimeMillis();
             String ext = item.getName().substring(item.getName().lastIndexOf(".")+1 , item.getName().length());
             filename = time+"."+ext;
             item.write(new File(savePath+"/"+filename));
        }
    }

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
        String sql = "insert into gallery(gallery_idx, title, writer, content, filename)";
        sql += " values(seq_gallery.nextval, ?, ?, ?, ?)";

        pstmt = con.prepareStatement(sql); // 쿼리문 준비객체

        // 바인드 변수 값 할당
        pstmt.setString(1, title);
        pstmt.setString(2, writer);
        pstmt.setString(3, content);
        pstmt.setString(4, filename);

        // 쿼리 수행 후 영향 받은 레코드의 수 반환
        int result = pstmt.executeUpdate(); // DML
        if(result>0) {
            out.print("<script>");
            out.print("alert('등록성공');");
            out.print("location.href='/gallery/list.jsp';"); // 리스트 요청
            out.print("</script>");
        }
        else {
            out.print("<script>");
            out.print("alert('등록실패');");
            out.print("history.back();"); // 리스트 요청
            out.print("</script>");
        }
    }
    if(pstmt != null)
        pstmt.close();
    if(con != null)
        con.close();
%>