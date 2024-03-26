package com.sds.openapp.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// 처리할 XML 파일을 읽어들여 각 태그마다 이벤트를 발생시키기 위한 객체를 상속
// DefaultHandler를 상속받는 순간부터 다루려고 하는 xml 파일을 처리할 수 있다
// 이벤트를 처리하는 리스너와 비슷

public class MyHandler extends DefaultHandler {

	// 태그의 이벤트가 종료되고 나면, 아래의 ArrayList는 DTO들로 채워져 있게 처리할 예정
	ArrayList<Member> list;
	Member member;
	
	// 태그 구분을 위한 논리값
	boolean isEmpno = false;
	boolean isEname = false;
	boolean isSal = false;

	// 문서가 시작될 때 호출되는 메서드
	public void startDocument() throws SAXException {
		// System.out.println("문서 시작");
		list = new ArrayList<Member>(); // 전체를 담을 배열 생성
	}
	
	// 여는 태그를 감지했을 때 호출되는 메서드
	// qName -> tag
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		// System.out.print("<"+tag+">");
		if(tag.equals("member")) // String 형의 내용 비교
			member = new Member(); // DTO 인스턴스 1개 생성
		else if(tag.equals("empno"))
			isEmpno = true;
		else if(tag.equals("ename"))
			isEname = true;
		else if(tag.equals("sal"))
			isSal = true;
	}
	
	// 태그 사이의 문자열을 만났을 때 호출되는 메서드
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length);
		// System.out.print(data);
		if(isEmpno)
			member.setEmpno(Integer.parseInt(data));
		else if(isEname)
			member.setEname(data);
		else if(isSal)
			member.setSal(Integer.parseInt(data));
	}
	
	// 닫는 태그를 감지했을 때 호출되는 메서드
	public void endElement(String uri, String localName, String tag) throws SAXException {
		// System.out.println("</"+tag+">");
		if(tag.equals("member"))
			list.add(member);
		else if(tag.equals("empno"))
			isEmpno = false;
		else if(tag.equals("ename"))
			isEname = false;
		else if(tag.equals("sal"))
			isSal = false;	
	}
	
	// 문서의 끝을 만나면 호출되는 메서드
	public void endDocument() throws SAXException {
		System.out.println("문서의 끝");
		System.out.println("메모리에 올라간 list의 수: "+list.size());
	}
}
