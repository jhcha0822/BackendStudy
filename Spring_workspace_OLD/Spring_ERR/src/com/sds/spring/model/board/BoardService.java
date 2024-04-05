package com.sds.spring.model.board;

import java.util.List;

import com.sds.spring.domain.Board;
import com.sds.spring.exception.BoardDMLException;

// 하위 컨트롤러가 서비스를 이용하기 위해 서로 간 결합도가 낮아야 하므로
// DI 구현을 위해 서비스 객체조차 인터페이스인 최상위 객체를 정의해놓음

public interface BoardService {
	
	public List selectAll(); 				// 모든 레코드 가져오기
	public Board select(int board_idx); 	// 데이터 한 건 가져오기
	public void insert(Board board); 		// 한 건 입력
	public void update(Board board);		// 한 건 수정
	public void delete(Board board);		// 한 건 삭제
}
