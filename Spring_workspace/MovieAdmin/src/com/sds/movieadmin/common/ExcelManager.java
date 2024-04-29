package com.sds.movieadmin.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.sds.movieadmin.domain.Movie;

@Component
public class ExcelManager {
	// 서버에 저장된 엑셀 파일을 접근하여 읽어들이기
	// 자바의 List 형태로 반환
	public List getMovieData(String excelPath) {
		List<Movie> movieList = new ArrayList<Movie>();
		
		try {
			XSSFWorkbook book = new XSSFWorkbook(excelPath);
			XSSFSheet sheet = book.getSheetAt(0);
			
			// row 수만큼 반복문 돌리기
			Iterator it = sheet.iterator();
			it.next(); // 제목 row는 넘기기
			while(it.hasNext()) {
				XSSFRow row = (XSSFRow)it.next();
				XSSFCell codeCell = row.getCell(0); // 영화코드
				XSSFCell nameCell = row.getCell(1); // 영화이름
				XSSFCell urlCell = row.getCell(2);	// url
				
				// Movie 객체에 row 한 건 담기
				Movie movie = new Movie();
				movie.setMovieCd(Integer.toString((int)codeCell.getNumericCellValue()));
				movie.setUrl((String)urlCell.getStringCellValue());
				
				movieList.add(movie);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movieList;
	}
		
}
