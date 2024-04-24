package com.sds.testapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sds.testapp.common.Pager;
import com.sds.testapp.domain.Board;
import com.sds.testapp.exception.BoardException;
import com.sds.testapp.model.board.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestBoardController { // 순수 데이터 요청 처리
	// templates/board/main과 같은 페이지 요청 처리는 일반 Controller에서 처리

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private Pager pager;
	
	// Logger logger = LoggerFactory.getLogger(this.getClass()); @Slf4j로 대체
		
	// 글쓰기 요청
	@PostMapping("/board")
	public ResponseEntity regist(Board board) {
		//log.trace("글쓰기 요청");
		log.trace("title: "+board.getTitle());
		log.trace("writer: "+board.getWriter());
		log.trace("content: "+board.getContent());
				
		boardService.insert(board);
		
		// 몸체 없이 헤더에 200을 담아 응답
		ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).build();
		
		return entity;
	}
	
	// 게시물 목록 요청
	@GetMapping("/board")
	public List getList(@RequestParam(value="currentPage", defaultValue="1") int currentPage) {
		log.trace("글등록 요청");
		
		pager.init(boardService.getTotalCount(), currentPage);
		Map map = new HashMap();
		map.put("startIndex", pager.getStartIndex());
		map.put("rowCount", pager.getRowCount());
		
		List boardList = boardService.selectAll(map);
		
		return boardList;
	}
	
	// 1건의 상세 정보
	@GetMapping("/board/{board_idx}")
	public Board getDetail(@PathVariable("board_idx") int board_idx) {
				
		Board board = boardService.select(board_idx);
	
		return board;
	}
	
	// 게시물 수정 요청
	@PutMapping("/board")
	public ResponseEntity update(Board board) {
		log.trace("수정 요청");
//		log.trace(board.getTitle());
//		log.trace(board.getWriter());
//		log.trace(board.getContent());
		boardService.update(board);
		ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).build();
		return entity;
	}
	
	// 게시물 삭제 요청
	@DeleteMapping("/board/{board_idx}")
	public ResponseEntity del(@PathVariable("board_idx") int board_idx) {
		log.trace("삭제 요청");
		Board board = new Board();
		board.setBoard_idx(board_idx);
		boardService.delete(board);
		ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).build();
		return entity;
	}
	
	// 검색 요청
	@GetMapping("/search/board")
	public List getListBySearch(Board board) {
		List boardList = boardService.selectBySearch(board.getTitle());
		return boardList;
	}

	@ExceptionHandler(BoardException.class)
	public ResponseEntity handle(BoardException e) {
		// 500
		ResponseEntity entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return entity;
	}
	
}
