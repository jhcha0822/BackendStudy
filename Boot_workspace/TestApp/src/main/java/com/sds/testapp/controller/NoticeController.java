package com.sds.testapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sds.testapp.common.Pager;
import com.sds.testapp.domain.Notice;
import com.sds.testapp.exception.NoticeException;
import com.sds.testapp.model.notice.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private Pager pager;
	
	// Spring Boot에서는 전송되어오는 매개변수를 DTO에 소속된 변수가 아닌 단독 변수로 받을 경우,
	// 특히 int 형으로 받고 싶을 때 디폴트 값 명시를 해야 함(String으로 수령)
	@GetMapping("/notice/list")
	public String getList(Model model, @RequestParam(value="currentPage", defaultValue="1") int currentPage) {
		pager.init(noticeService.getTotalCount(), currentPage);
		
		// 레코드를 가져올 때, 어디서부터 몇 건을 가져올 지 명시
		Map map = new HashMap(); // n부터 m개의 레코드를 가져오기 위한 매개변수
		map.put("startIndex", pager.getStartIndex());
		map.put("rowCount", pager.getRowCount());
		System.out.println(pager.getStartIndex() + " " + pager.getRowCount());
		List noticeList = noticeService.selectAll(map); //3단계: 일시키기 
		model.addAttribute("noticeList", noticeList); //4단계: 결과 저장 
		model.addAttribute("pager", pager);
		return "notice/list";
	}
	
	@GetMapping("/notice/writeform")
	public String getRegistForm() {
		return "notice/write";
	}
	
	// 글쓰기 요청 처리
	@PostMapping("/notice/regist")
	public ModelAndView regist(Notice notice) {
		noticeService.insert(notice);
		ModelAndView mav = new ModelAndView("redirect:/notice/list");
		return mav;
	}
	
	// 상세보기 요청 처리
	@GetMapping("/notice/detail")
	public String getDetail(Model model, @RequestParam(value="notice_idx", defaultValue="0") int notice_idx) {
		Notice notice = noticeService.select(notice_idx);
		model.addAttribute("notice", notice);
		return "notice/content";
	}
	
	// 글 수정 요청 처리
	@PostMapping("/notice/edit")
	public String edit(Notice notice) {
		noticeService.update(notice);
		return "redirect:/notice/content?notice_idx="+notice.getNotice_idx();
	}
	
	// 글 삭제 요청 처리
	@PostMapping("/notice/delete")
	public String delete(Notice notice) {
		noticeService.delete(notice);
		return "redirect:/notice/list";
	}
	
	
	// 컨트롤러가 보유한 메서드 중 예외가 발생하는 메서드가 있다면 해당 메서드의 실행부는 해당 라인에서 아래의 메서드로 진입
	@ExceptionHandler(NoticeException.class)
	public ModelAndView handle(NoticeException e) {
		ModelAndView mav = new ModelAndView("error/result"); // Spring 설정파일에 접두어 적용되어 있음
		mav.addObject("e", e);
		return mav;
	}
	
}
