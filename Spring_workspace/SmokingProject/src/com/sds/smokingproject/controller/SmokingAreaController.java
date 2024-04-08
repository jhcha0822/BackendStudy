package com.sds.smokingproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sds.smokingproject.domain.SmokingArea;
import com.sds.smokingproject.model.smokingarea.SmokingAreaService;



// servlet-context.xml 내의 DefaultAnnotaionHandlerMapping이
// 아래의 클래스를 하위 컨트롤러로 발견하게끔, 어노테이션으로 표시를 해야 한다
@Controller
public class SmokingAreaController {

	@Autowired
	private SmokingAreaService smokingAreaService;
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String getList(Model model) {
		List<SmokingArea> smokingAreaList = smokingAreaService.selectAll();
		
		model.addAttribute("smokingAreaList", smokingAreaList);
		
		return "redirect:/list";		
	}
	
}
