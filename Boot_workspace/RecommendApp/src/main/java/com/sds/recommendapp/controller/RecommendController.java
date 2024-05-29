package com.sds.recommendapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sds.recommendapp.domain.Member;
import com.sds.recommendapp.model.recommend.RecommendService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

// 영화 추천 요청 처리
@Slf4j
@Controller
public class RecommendController {

	@Autowired
	private RecommendService recommendService;
	
	@GetMapping("/")
	public String getList(HttpSession session, Model model) {
		Member member = (Member)session.getAttribute("member");
		
		List commentsList = recommendService.getList(member.getMember_idx());
		model.addAttribute("recommendList", commentsList);
		
		return "recommend/list";
	}
	
}
