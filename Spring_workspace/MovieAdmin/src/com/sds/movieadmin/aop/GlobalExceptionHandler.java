package com.sds.movieadmin.aop;

import org.apache.commons.fileupload.FileUploadBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sds.movieadmin.exception.UnAuthorizedException;

// 하위 컨트롤러를 포함하여 어플리케이션의 전반적 영역에 걸쳐 발생하는 예외는
// 아래의 어노테이션만 명시하면 이 클래스에서 모두 처리가 가능하다
// 따라서 예외 처리를 파편화시키지 않고, 하나의 공통된 클래스에서 관리하자 (@ControllerAdvice 이용)
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UnAuthorizedException.class)
	public String handle(UnAuthorizedException e) {
		
		return "admin/error/result";
	}
	
	@ExceptionHandler(FileUploadBase.SizeLimitExceededException.class)
	public ResponseEntity handle(FileUploadBase.SizeLimitExceededException e) {
		
		// 응답코드 생성
		ResponseEntity entity = ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).build(); // 413: 너무 큰 파일용량
		
		return entity;
	}
	
}
