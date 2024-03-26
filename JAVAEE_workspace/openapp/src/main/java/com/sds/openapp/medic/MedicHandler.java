package com.sds.openapp.medic;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// 읽어들인 XML 문서를 분석하기 위한 핸들러
// 각 태그마다 이벤트가 발생, 적절한 처리

public class MedicHandler extends DefaultHandler{

	ArrayList<Hospital> list; // DTO를 담을 리스트
	ListServlet listServelt;
	
	boolean isAddr; //주소 addr
	boolean isXPos; //위도 XPos
	boolean isYPos; //경도 YPos
	boolean isYadmNm; //병원명 yadmNm
	
	Hospital dto;
	
	public MedicHandler(ListServlet listServlet) {
		this.listServelt = listServlet;
	}
	
	public void startDocument() throws SAXException {

	}
	
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		if(tag.equals("items"))
			list = new ArrayList<Hospital>();
		else if(tag.equals("item"))
			dto = new Hospital(); // 비어 있는 DTO 설정
		else if(tag.equals("addr"))
			isAddr = true;
		else if(tag.equals("XPos"))
			isXPos = true;
		else if(tag.equals("YPos"))
			isYPos = true;
		else if(tag.equals("yadmNm"))
			isYadmNm = true;
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length);
		
		if(isAddr)
			dto.setAddr(data);
		else if(isXPos)
			dto.setLongi(Double.parseDouble(data));
		else if(isYPos)
			dto.setLati(Double.parseDouble(data));
		else if(isYadmNm)
			dto.setName(data);
	}
	
	public void endElement(String uri, String localName, String tag) throws SAXException {
		if(tag.equals("item"))
			list.add(dto);
		else if(tag.equals("addr"))
			isAddr = false;
		else if(tag.equals("XPos"))
			isXPos = false;
		else if(tag.equals("YPos"))
			isYPos = false;
		else if(tag.equals("yadmNm"))
			isYadmNm = false;
	}
	
	public void endDocument() throws SAXException {
		listServelt.createResponseData();
	}
}
