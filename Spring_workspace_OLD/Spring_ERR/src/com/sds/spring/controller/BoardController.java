package com.sds.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sds.spring.domain.Board;
import com.sds.spring.exception.BoardDMLException;
import com.sds.spring.model.board.BoardService;

// DefaultAnnotationHandlerMapping이 아래 클래스를 하위 컨트롤러로 인식하게끔 어노테이션 표시 
// 더이상 implements Controller 사용하지 않는다

// Controller -> service -> DAO -> manager

@Controller
public class BoardController {
	// 앞으로 게시판과 관련된 요청을 이 하나의 클래스에서 담당하되
	// 각각의 요청은 메서드로 처리한다
	
	@Autowired
	private BoardService boardService;
	
	// 목록 요청
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public String getList(Model model) {
		List boardList = boardService.selectAll();
		
		model.addAttribute("boardList", boardList); // 결과 저장

		// View는 이름을 반환 String에 넣으면 객체에 자동적으로 담긴다
		return "board/list";
	}
	
	// 글쓰기 폼 요청
	@RequestMapping(value="/board/writeform", method=RequestMethod.GET)
	public String getForm() {
		return "board/write"; // 요청 유지
	}
	
	// 글 등록 요청
	@RequestMapping(value="/board/regist", method=RequestMethod.POST)
	public String regist(Board board) {
		boardService.insert(board);
		return "redirect:/board/list"; // 목록을 재요청(redirect)해야하기에 위 메서드를 이용
	}
	
	// 글 상세보기 요청 (MaV 사용도 가능)
	@RequestMapping(value="/board/detail", method=RequestMethod.GET)
	public ModelAndView getDeatil(Board dto) {
		Board board = boardService.select(dto.getBoard_idx());
		ModelAndView mav = new ModelAndView("board/content");
		mav.addObject("board", board);
		return mav;
	}
	
	// 글 수정 요청
	@RequestMapping(value="/board/update", method=RequestMethod.POST)
	public String edit(Board board) {
		boardService.update(board);
		return "redirect:/board/detail?board_idx="+board.getBoard_idx(); // redirect 재요청
	}
	
	// 글 삭제 요청
	@RequestMapping(value="/board/delete", method=RequestMethod.POST)
	public String del(Board board) {
		boardService.delete(board);
		return "redirect:/board/list";
	}
	
	//이 컨트롤러의 메서드에서 만일 예외가 발생하면, 아래의 메서드가 자동호출됨
	@ExceptionHandler(BoardDMLException.class)
	public ModelAndView handle(BoardDMLException e) {
		
		//개발자가 런타임 예외에 심어놓은 메시지를 꺼낼때는 e.getMessage() 메서드 사용 
		System.out.println("예외 발생 "+ e.getMessage());
		
		return null;
	}
}
