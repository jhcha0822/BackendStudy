package com.sds.testapp.common;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Pager {
	private int totalRecord; 		// 총 레코드 수
//	private int pageSize = 10;  	// 페이지당 레코드 수
	private int rowCount = 10; 		// 페이지 내 mysql에서 불러올 글의 개수: limit문의 두번째 매개변수
	private int totalPage; 			// 총 페이지 수
	private int blockSize = 10; 	// 블럭당 페이지 수
	private int currentPage = 1; 	// 현재 페이지
	private int firstPage;			// 블럭 내 반복문의 시작값
	private int lastPage; 			// 블럭 내 반복문의 끝 값
//	private int curPos; 			// 페이지 내 List의 시작 index
	private int startIndex; 		// 페이지 내 mysql에서 불러올 시작 index: limit문의 첫 매개변수
	private int num; 				// 페이지 내 시작 글번호
	
	public void init(int totalRecord, int currentPage) {
		// limit을 이용하기에 모든 레코드를 가져오지 않음: 모든 레코드 수는 별도로 조사하여 전달
		this.totalRecord = totalRecord;
		this.totalPage = (int)Math.ceil((float)totalRecord/rowCount);
		this.currentPage = currentPage;
		this.firstPage = this.currentPage-(this.currentPage-1)%blockSize;
		this.lastPage = this.firstPage+(this.blockSize-1);
		if(this.totalPage < this.lastPage)
			this.lastPage = this.totalPage;
		this.startIndex = (this.currentPage-1)*this.rowCount;
		this.num = this.totalRecord-this.startIndex;
	}
	
	
}
