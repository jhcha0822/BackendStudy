package com.sds.mall.model.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sds.mall.domain.Product;
import com.sds.mall.exception.UploadException;

// FileManager가 Spring의 bean으로 등록되어 있다면
// ServletContext를 자동 주입받을 수 있다

// 파일과 관련된 업무만을 처리하는 모델 객체
@Component
public class FileManager {
	
	@Autowired
	private ServletContext servletContext; // DispatcherServlet -> ApplicationContext(스프링 컨테이너) -> Application 내장객체 
										   // getRealPath 사용 가능
	
	@Autowired
	private String savePath;
	
	// 서버의 지정된 경로에 파일로 저장하는 메서드
	public void save(Product product) {// throws UploadException { // throws: 메서드 호출자에게 예외 처리를 전가

		// product 안의 photo를 꺼내기
		MultipartFile photo = product.getPhoto();
		
		// 서버 디렉토리에 저장
		try {
			String realPath = servletContext.getRealPath(savePath); // 외부에서 주입받은 경로로 전체 경로 얻기
			
			String filename = photo.getOriginalFilename();
			String ext = getExt(filename);
			String newName = createFilename(ext);
			
			photo.transferTo(new File(realPath+newName)); // 지정한경로+파일명
			System.out.println(realPath+newName);
			
			// Product DTO는 컨트롤러부터 모델 영역에서 주소값이 공유되고 있음: 매개변수
			product.setFilename(newName);
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// catch문을 작성하면서 로그 출력만 했다면
			// 외부 계층에서는 에러 발생 여부를 알 수 없다.
			// 따라서 에러의 정보를 외부로 전달한다.
			// 전달을 위해 개발자 정의 에러를 생성한다.
			// throw new UploadException("업로드 실패\n문제 지속 발생시 업로드할 파일의 크기 확인", e);
			// catch 하거나 외부로 throws 해야됨
		}	
	}
	
	// 확장자 추출
	public String getExt(String path) {
		return path.substring(path.lastIndexOf(".")+1, path.length());
	}
	
	// 파일명 생성 : 현재 ms이용, 확장자를 넘겨주면 완성된 파일명 반환
	public String createFilename(String ext) {
		long time = System.currentTimeMillis();
		return time+"."+ext;
	}
	
	
}
