package com.sds.model2app.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;

// 페이징처리를 작성 시 코드가 중복되고 복잡해지기 때문에
// 페이징 처리를 전담하는 공통객체를 정의
@Getter
public class Pager {
	private int totalRecord; 		// 총 레코드 수
	private int pageSize = 10; 		// 페이지당 보여질 레코드 수
	private int totalPage; 			// 총 페이지 수
	private int blockSize = 10; 	// 블럭당 보여질 페이지 수
	private int currentPage = 1; 	// 유저가 보고있는 페이지
	private int firstPage;			// 블럭의 시작 페이지
	private int lastPage;			// 블럭의 마지막 페이지
	private int curPos;				// 페이지당 list의 시작 인덱스
	private int num;				// 페이지 시작 레코드 번호
	
	// 페이징 처리 계산 메서드
	public void init(List list, HttpServletRequest request) {
		totalRecord = list.size();
		totalPage = (int)Math.ceil((float)totalRecord/pageSize);

		// 사용자가 선택한 페이지 번호로 currentPage 설정
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		firstPage = currentPage-(currentPage-1)%blockSize;
		lastPage = firstPage + (blockSize-1);
		
		curPos = (currentPage-1)*pageSize;
		num = totalRecord - curPos;		
	}
}
