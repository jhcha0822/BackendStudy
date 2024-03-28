package com.sds.dataroom.excel;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.*;

// 자바로 엑셀을 연동하기 위해서는
// apache사의 excelPOI 이용

public class ExcelRead {
	public static void main(String[] args) {
		// HSSFWorkbook은 구버전(.xls)
		// XSSFWorkbook은 최신버전(.xlsx)
		// 하드 디스크에 있는 파일을 대상으로 Workbook 객체 생성
		try {
			// Workbook 엑셀 파일 접근
			Workbook workbook = new XSSFWorkbook("D:\\MULTICAMPUS\\JAVAEE_workspace\\Dataroom\\src\\main\\webapp\\data\\emp.xlsx");
			// Sheet 접근
			Sheet sheet = workbook.getSheetAt(0);
			// Sheet는 row(행)들을 iterator로 반환해준다
			Iterator<Row> rowIt = sheet.iterator();
			
			while(rowIt.hasNext()) { // row 만큼 반복
				Row row = rowIt.next();
				Iterator<Cell> cellIt = row.iterator(); // 컬럼
				while(cellIt.hasNext()) { // 하나의 row를 구성하는 collumn 수 만큼 반복
					Cell cell = cellIt.next();
					if(cell.getCellType() == CellType.STRING) { // 문자형이라면
						System.out.println(cell.getStringCellValue()); // 문자로 접근
					} else if(cell.getCellType() == CellType.NUMERIC) { // 숫자형이라면
						System.out.println(cell.getNumericCellValue()); // 숫자로 접근
					}
				}
				System.out.println("------");
			}
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
}
