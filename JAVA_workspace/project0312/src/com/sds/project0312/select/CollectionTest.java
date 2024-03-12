//package com.sds.project0312.select;
//
//import java.util.ArrayList;
//
//// 컬렉션 프레임워크는 개발자가 특별한 제한을 가하지 않으면 객체들이 섞여 들어가는 것을 허용함
//// 개발자가 이 부분이 마음에 들지 않는다면, 사용하고자 하는 컬렉션 프레임워크 객체의 자료형을 특정 자료형으로 제한 가능
//// 이 기법을 Generic Type이라 한다.
//
//// Generic 사용시 장점
//// 1) 자료형을 섞이지 않게 제한 가능
//// 2) 컬렉션 프레임워크는 넣거나 뺄 때 Object형으로 사용해야 하지만 제너릭 이용시 제너릭으로 지정한 자료형으로 이용가능
////     따라서 형변환이 불필요함
//public class CollectionTest {
//	public static void main(String[] args) {
//		ArrayList<String> list = new ArrayList<String>();
//		
//		// 컴파일 타임, 문법 검사 시점부터 제한을 가해줌
//		list.add("banana");
//		list.get(0); // 꺼내올때도 String형이 되어 있음
//		
//		// Object obj = list.get(0);
//		// (String)obj;
//		// obj.charAt(0);
//	}
//}
