package com.sds.movieapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.movieapp.domain.CustomUserDetails;
import com.sds.movieapp.domain.Member;
import com.sds.movieapp.domain.Movie;
import com.sds.movieapp.model.movie.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/movie/detail")
	public String getDetail(Model model, @RequestParam(value="movie_idx", defaultValue="0") int movie_idx) {
		
		//최종적으로 우리 mysql의 정보와 + open API의 영화정보가 합쳐진 DTO 가져옴 
		Movie movie = movieService.select(movie_idx);//영화 한건 조회 
		// System.out.println("영화 url은 "+movie.getUrl());
		// System.out.println("영화명 "+movie.getMovieNm());
		
		model.addAttribute("movie", movie);
		
//		// Spring security가 로그인을 처리했기에 Spring Security를 통해 유저 정보를 꺼내
//		// detail.html에서 사용할 수 있도록 request 객체에 저장해놓아야 함
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		// 로그인 하지 않았을 경우 스프링 시큐리티가 auth.getPrincipal() 반환으로 anonymousUser를 반환: 조건문 사용
//		if(auth.getPrincipal() instanceof CustomUserDetails) {
//			CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal();
//			Member member = userDetails.getMember();
//			model.addAttribute("member", member);
//		}		
//		// String nickname = auth.getName();
//		// model.addAttribute("nickname", nickname);
		
		return "movie/detail";
	}
	
}
