package com.sds.movieadmin.model.movie;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.movieadmin.common.ExcelManager;
import com.sds.movieadmin.common.FileManager;
import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.exception.MovieException;
import com.sds.movieadmin.exception.UploadException;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private FileManager fileManager; // root.xml에 있음
	
	@Autowired
	private ExcelManager excelManager;
	
	@Autowired
	private MovieApiService movieApiService;
	
	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return movieDAO.selectCount();
	}

	@Override
	public List selectAll(Map map) {
		// TODO Auto-generated method stub
		List siteMovieList = movieDAO.selectAll(map);
		// 영화코드 이용하여 API 조회 -> 빈 정보를 DTO에 넣기
		
		return movieDAO.selectAll(map);
	}
	
	// 1건
	@Override
	public void regist(Movie movie) throws MovieException {
		// TODO Auto-generated method stub
		// DB
		movieDAO.insert(movie);
	}

	// 다수
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registExcel(Movie movie) throws UploadException, MovieException {
		// TODO Auto-generated method stub
		
		// 파일 저장
		String excelPath = fileManager.save(movie);
		
		// 파일을 읽기
		List<Movie> movieList = excelManager.getMovieData(excelPath);
		
		// 기존 레코드 존재 시 삭제
		movieDAO.deleteAll();
		
		// DB에 저장
		for(Movie dto : movieList) {
			movieDAO.insert(dto);
		}
		
	}
	
}
