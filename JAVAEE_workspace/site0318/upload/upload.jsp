<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="java.io.File" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>

<%
    // 클라이언트가 전송한 텍스트(제목) 및 바이너리 파일을 넘겨받아 서버의 원하는 경로에 저장하자==업로드
    
    // 클라이언트가 요청을 시도하면, 서버측에서는 요청을 처리하기 위한 내장객체들이 메모리에 생성되는데
    // 요청내용과 관련된 객체는 request이고, 응답정보를 구성하기 위한 객체는 response 객체이다.
    
    // 요청시 전송되어온 파라미터에 인코딩을 지정해야 한글이 깨지지 않음
    request.setCharacterEncoding("utf-8");

    // String title = request.getParameter("title");
    // out.print(title);

    // 클라이언트가 multipart/form-data 형식으로 데이터를 전송할 경우, 문자열로 취급해서는 안된다
    // 위와 같은 request.getParameter()는 문자열을 취급하는 메서드이므로
    // 복합된 데이터 형식인 upload 처리가 불가함
    // upload 컴포넌트를 이용해야 함

    // 바이너리 파일은 자체가 String으로 처리될 수 없기 때문에
    // 또한 파일을 서버에 저장해야 하는 복잡한 과정을 거쳐야 하기 때문에 별도의 업로드 컴포넌트를 이용해야 함

    // 첫번째 생성자
    String savePath = "D:/MULTICAMPUS/JAVAEE_workspace/site0318/data";
    // MultipartRequest multi = new MultipartRequest(request, savePath); // cos.jar는 생성자 호출만으로 업로드 진행
    // out.print("업로드 성공");
    // cos.jar에서는 기존 request의 메서드명을 유지함
    // 이 생성자는 한글이 깨진다는 문제가 있음

    // 네번째 생성자: 인코딩 적용 가능
    int maxSize = 1*1024*1024;
    String encoding = "utf-8";
    MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, encoding);
    String title = multi.getParameter("title");
    // out.print(title+"<br>");

    // 오라클에 제목과 파일명 넣기
    long time = System.currentTimeMillis();
    // out.print(time+"<br>");

    // 확장자는 방금 업로드된 파일명을 얻어와 처리
    String photo = multi.getOriginalFileName("photo");
    // out.print(photo);
    // 파일의 경로에서 가장 마지막 . 다음부터 문자열의 마지막까지 구한다
    String ext = photo.substring(photo.lastIndexOf(".")+1, photo.length());
    // out.print(ext+"<br>");
    String filename = time+"."+ext;

    // 업로드 컴포넌트에 의해 이미 서버에 저장되어진 파일을 File 클래스로 얻어와서
    // 이름을 새롭게 바꾸기
    File ori = new File(savePath+"/"+photo); // 파일 클래스로 원본 객체 얻어오기
    ori.renameTo(new File(savePath+"/"+filename));

    // 오라클에 넣기
    // JDBC 코드 작성
    // 1) 드라이버 로드
    // 2) 접속
    // 3) 쿼리문 수행
    // 4) 자원 해제

    // 1) 드라이버 로드
    Class.forName("oracle.jdbc.driver.OracleDriver");
    out.print("드라이버 로드 성공<br>");

    // 2) 접속
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String user = "seshop";
    String pw = "1234";

    Connection con = null;
    PreparedStatement pstmt = null;
    // java.sql.DriverManager.getConnection(url, user, pw); 도 가능하긴 함
    con = DriverManager.getConnection(url, user, pw);
    
    if(con != null) {
        out.print("접속 성공<br>");
        String sql = "insert into gallery(gallery_idx, title, filename)";
        sql += " values(seq_gallery.nextval, ?, ?)";
        // 물음표는 바인드 변수를 의미. 바인드 변수값을 반드시 지정 후, 쿼리문 수행
        
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, title); // 첫번째 바인드 변수값 지정
        pstmt.setString(2, filename); // 두번째 바인드 변수값 지정

        int result = pstmt.executeUpdate(); // 쿼리 수행 후 반영된 레코드 수 반환
        if(result<1)
            out.print("입력 실패<br>");
        else
            out.print("입력 성공<br>");
    }

    if(pstmt != null)
        pstmt.close();
    if(con != null)
        con.close();

%>