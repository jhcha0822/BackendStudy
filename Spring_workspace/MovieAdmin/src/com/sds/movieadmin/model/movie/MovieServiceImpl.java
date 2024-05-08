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
import com.sds.movieadmin.domain.MovieDoc;
import com.sds.movieadmin.exception.MovieException;
import com.sds.movieadmin.exception.UploadException;
import com.sds.movieadmin.model.mongo.MongoMovieDAO;

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
	
	@Autowired
	private MongoMovieDAO mongoMovieDAO;
	
	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return movieDAO.selectCount();
	}

	@Override
	public List selectAll(Map map) {
		// TODO Auto-generated method stub
		List<Movie> siteMovieList = movieDAO.selectAll(map);
		// 영화코드 이용하여 API 조회 -> 빈 정보를 DTO에 넣기
		for(Movie movie : siteMovieList) {
			// OpenAPI 호출 객체에게 Movie DTO를 맡겨 정보를 더 받자
			movieApiService.getMovie(movie);
		}
		return siteMovieList;
	}
	
	// 1건
	@Override
	public void regist(Movie movie) throws MovieException {
		// TODO Auto-generated method stub
		// DB
		movieDAO.insert(movie);
	}

	//등록업무는 파일저장+db insert가 모두 성공해야, 전체를 성공으로 처리하게됨
	//따라서 이 서비스에서 파일저장은 FileManager에게 일을 전담시키고, db insert는 
	//MovieDAO에게 업무를 전담시켜, 예외가 발생할 경우 모두 없었던 일로 무효화
	// 다수(엑셀로)
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
		mongoMovieDAO.delete();
		
		// DB에 저장
		for(Movie dto : movieList) {
			movieDAO.insert(dto); // selectKey에 의해 movie_idx가 채워짐
			// mongoDB movie collection에 insert
			MovieDoc doc = new MovieDoc();
			doc.setMovie_idx(dto.getMovie_idx());
			
			Movie result = movieApiService.getMovie(dto); // API를 통해 정보를 가져옴
			doc.setMovieNm(result.getMovieNm());			
			
			// 리스트를 꺼내어 배열로 옮기기
			String[] genres = new String[result.getGenres().size()];
			for(int i=0; i<genres.length; i++) {
				genres[i] = result.getGenres().get(i).getGenreNm();
			}
			doc.setGenres(genres); // genre 배열 대입
			
			String[] directors = new String[result.getDirectors().size()];
			for(int i=0; i<directors.length; i++) {
				directors[i] = result.getDirectors().get(i).getPeopleNm();
			}
			doc.setDirectors(directors);
			
			String[] actors = new String[result.getActors().size()];
			for(int i=0; i<actors.length; i++) {
				actors[i] = result.getActors().get(i).getPeopleNm();
			}
			doc.setActors(actors);
			
			String[] nations = new String[result.getNations().size()];
			for(int i=0; i<nations.length; i++) {
				nations[i] = result.getNations().get(i).getNationNm();
			}
			doc.setNations(nations);
			mongoMovieDAO.insert(doc);
		}
		
	}
	
}
