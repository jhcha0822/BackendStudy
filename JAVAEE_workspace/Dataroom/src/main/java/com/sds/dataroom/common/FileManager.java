package com.sds.dataroom.common;


// 파일과 관련된 처리
public class FileManager {
	
	// 확장자 구하기
	public static String getExt(String path) {
		return path.substring(path.lastIndexOf(".")+1, path.length());
	}
	
	// 현재 시간으로 파일명 생성. 확장자는 매개변수로 넘겨받음
	public static String getNameByTime(String ext) {
		long time = System.currentTimeMillis();
		return time+"."+ext;
	}
	
}
