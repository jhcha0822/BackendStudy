/*
package com.sds.SEShop.lib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionManager {
	
	public static String getConvertedData(String password){
		//String password="minzino"; //암호화의 대상이 되는 데이터 
		
		String hash=""; //최종적인 암호화 결과를 담게될 스트링
		
		//암호화 알고리즘을 처리하는 객체 MessageDigest 객체
		try {
			MessageDigest digest= MessageDigest.getInstance("SHA-256"); //64자 해시값 만들기
			System.out.println("다이제스트 생성 성공");
			
			byte[] bytes = password.getBytes(); //스트링을 바이트 배열로 변환

			for(int i=0;i<bytes.length;i++) {
				System.out.println(bytes[i]);
			}
			
			bytes = digest.digest(bytes); //암호화시킬 대상 데이터를 byte[] 형 배열로 넣어줘야 함
			System.out.println("다이제스트가 처리한 이후의 배열의 길이 "+ bytes.length);
			
			
			for(int i=0;i<bytes.length;i++) {
				//System.out.println(bytes[i]);
				String hex = Integer.toHexString(0xff & bytes[i]);
				System.out.println(hex);
				if(hex.length()==1) {
					hex+="0";
				}
				hash +=hex;
			}
			
			System.out.println(hash.length());	
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("알고리즘 이름을 확인해 주세요");
		}
		
		return hash;
	}

}
*/

package com.sds.SEShop.lib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionManager {

	// 암호화 방식
	// 1) 양방향 (복호화 가능)
	//     대칭키
	//     비대칭키(공개키)
	// 2) 단방향 (복호화 불가)
	//     해시함수: 암호화 시키면 원본을 추측할 수 없음
	//     - 동일한 길이의 암호 반환
	//     - 중복되지 않음
	//public static void main(String[] args) {
	public static String getConvertedData(String password) {
		//String password = "plaintext";
		String hash = "";
		// SE 에는 암호화 알고리즘을 처리하는 객체 MessageDigest가 존재
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			byte[] bytes = password.getBytes(); // String -> byte array
			//System.out.println("배열의 길이: "+bytes.length);
			//for(int i=0; i<bytes.length; i++)
				//System.out.println(bytes[i]);
			
			bytes = digest.digest(bytes);
			//System.out.println("배열의 길이: "+bytes.length);
			for(int i=0; i<bytes.length; i++) {
				//System.out.println(bytes[i]);
				String hex = Integer.toHexString(0xff & bytes[i]);
				//System.out.println(hex);
				if(hex.length() == 1) // 빈 공간에 0 추가
					hex+="0"; // 부하 걸리는 코드
				hash += hex;
			}
			//System.out.println(hash);
			//System.out.println(hash.length());
			
			//digest.digest(password.getBytes()); // 암호화시킬 대상 데이터를 바이트형 배열로 넣어줘야 함
			//System.out.println();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("알고리즘 확인 필요");
		}
		return hash;
	}
}
