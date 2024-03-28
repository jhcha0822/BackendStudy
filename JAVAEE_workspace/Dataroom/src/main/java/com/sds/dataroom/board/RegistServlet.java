package com.sds.dataroom.board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sds.dataroom.common.FileManager;

// 바이너리 파일이 포함된 글쓰기 요청을 처리하는 서블릿
public class RegistServlet extends HttpServlet{
	
	DataroomDAO dataroomDAO = new DataroomDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 클라이언트가 바이너리 파일을 첨부하여 보낼 경우 encoding이 multipart/form-data
		// 기존의 request.getParameter()로는 문자열만 처리가 가능하기에 복합된 encoding 파라미터 받기가 불가능
		// 업로드 컴포넌트를 통하여 파라미터들을 처리해야 함
		System.out.println("접속 감지");
		
		// 업로드와 관련된 설정: 저장경로, 파일용량 DiskFileItemFactory
		// 파일업로드를 담당하는 클래스: ServletFileUpload
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1*1024*1024); // 1MB
		// jsp의 내장 객체의 종류
		// HttpServletRequest request(요청 정보를 가진 객체)
		// HttpServletResponse response(응답 정보를 가진 객체)
		// HttpSession session(세션 정보를 가진 객체)
		// JSPWriter out(응답할 문자열을 담고 있는 출력스트림 객체)
		// ServletContext application(어플리케이션 자체 정보를 가진 객체)
		
		ServletContext context = request.getServletContext(); // 어플리케이션 정보를 가진 객체를 얻는다
		String realPath = context.getRealPath("/data"); // 실제 경로를 얻어온다
		// System.out.println(realPath);
		
		// 파일이 저장될 서버측 경로
		File file = new File(realPath);
		factory.setRepository(file);
		
		// 업로드된 파라미터 추출
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> itemList = upload.parseRequest(request); // 업로드 분석
			
			String title = null;
			String writer = null;
			String content = null;
			String filename = null;
			
			for(FileItem item : itemList) { // 업로드된 파라미터 추출
				if(item.isFormField()) { // html의 text 박스라면
					if(item.getFieldName().equals("title")) // text 컴포넌트가 제목이라면
						title = item.getString("utf-8");
					else if(item.getFieldName().equals("writer")) // text 컴포넌트가 작성자라면
						writer = item.getString("utf-8");
					else if(item.getFieldName().equals("content")) // text 컴포넌트가 내용이라면
						content = item.getString("utf-8");
				} else { // html의 file upload 컴포넌트라면
					if(item.getName().length() >= 5) { // 파일명의 길이가 5 이상이면
						// 서버에 저장
						// System.out.println("업로드 한 파일명은 "+item.getName());
						filename = FileManager.getNameByTime(FileManager.getExt(item.getName()));
						try {
							item.write(new File(realPath+"\\"+filename)); //하드 디스크에 파일저장
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			// DAO에게 전달
			Dataroom dataroom = new Dataroom();
			dataroom.setTitle(title);
			dataroom.setWriter(writer);
			dataroom.setContent(content);
			dataroom.setFilename(filename);
			int result = dataroomDAO.insert(dataroom);
			
			if(result>0) { // 성공
				response.sendRedirect("/board/list.jsp"); // 클라이언트의 브라우저로 하여금 지정한 URL로 재접속을 하게 만드는 메서드
			} else { // 실패
				out.print("<script>");
				out.print("history.back();");
				out.print("</script>");
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
}

// realPath: D:\MULTICAMPUS\JAVAEE_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Dataroom\data