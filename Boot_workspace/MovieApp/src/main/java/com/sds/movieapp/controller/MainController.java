package com.sds.movieapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.movieapp.common.Pager;
import com.sds.movieapp.document.Movie;
import com.sds.movieapp.domain.MovieType;
import com.sds.movieapp.model.movie.MovieService;

@Controller
public class MainController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private Pager pager;
	
	@GetMapping("/")
	public String getMain(Model model, @RequestParam(value="currentPage", defaultValue="1") int currentPage) {
		
		pager.init(movieService.selectCount(), currentPage);
		
		// 영화 10건 가져오기
		Map map = new HashMap();
		map.put("startIndex", pager.getStartIndex()); 			// n번째부터
//		map.put("rowCount", pager.getPageSize()); 	  			// n건
		map.put("rowCount", 3);
		List<Movie> movieList = movieService.selectAll(map);	// 가져오기
		model.addAttribute("movieList", movieList);

		// AOP에서 처리함
//		// 카테고리 설정을 위해 타입 받아오기
//		List<MovieType> movieTypeList = movieService.getMovieTypeList();
//		model.addAttribute("movieTypeList", movieTypeList);
//		model.addAttribute("movieTypeList", movieTypeList);//4단계: 뷰에 보여줄 결과 저장
		
		// Spring Security를 통해 로그인한 사용자의 이름 가져오기
		String nickname=SecurityContextHolder.getContext().getAuthentication().getName();
		
		model.addAttribute("movieList", movieList);
		model.addAttribute("nickname", nickname);
		
		return "main/index";
	}
	
}
