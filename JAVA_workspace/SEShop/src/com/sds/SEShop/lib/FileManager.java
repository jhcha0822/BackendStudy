package com.sds.SEShop.lib;

// 파일과 관련된 유용한 기능을 제공

public class FileManager {
	public static String getExt(String filename) { // static으로 사용시 new 할 필요 없음
		int dotIndex = filename.lastIndexOf("."); 
		String ext = filename.substring(dotIndex+1, filename.length());
		// System.out.println("확장자: "+ext);
		return ext;
	}
}
