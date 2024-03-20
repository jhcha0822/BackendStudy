package com.sds.sessionapp.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionManager {
	// 평문을 해시값으로 변경해 반환하는 메서드 정의
	public static String getHashFromText(String text) {
		// 스트링은 상수이므로 편집 또는 변경이 불가능함
		// String을 대상으로 누적식을 하게 되면, 누적 수만큼 새 문자열 상수가 생성된다.
		// 해결: 변경 가능한 문자열 클래스 StringBuilder, StringBuffer
		StringBuilder sb = new StringBuilder();
		// 암호화 알고리즘 선택
		try {
			byte[] bytes = text.getBytes();
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] result = digest.digest(bytes);
			for(int i=0; i<result.length; i++) {
				String hex = Integer.toHexString(0xff &result[i]); // 16진수 문자열로 변환
				if(hex.length()==1)
					sb.append("0");
				sb.append(hex);
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}		
		return sb.toString();
	}
}
