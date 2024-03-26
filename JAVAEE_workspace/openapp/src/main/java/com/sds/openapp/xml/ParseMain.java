package com.sds.openapp.xml;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

// 자바 뿐만 아니라 일반적인 응용프로그램에서 xml은 문서이기 때문에 직접 데이터를 주고받을 수 없다
// 따라서 해석이나 분석의 과정이 필요하고, 이를 Parsing 파싱 이라 한다

// 자바에서 xml을 파싱하는 방법에는 크게 2가지가 있다
// 1) DOM 방식: 태그마다 1:1대응되는 객체를 메모리에 올려놓고 그 객체를 통해 문서를 간접 접근
//			   스마트폰이나 메모리가 작은 디바이스라면 사용되지 않는다
// 2) SAX 방식: 태그를 메모리에 올리지 않고 태그가 발견될 때 마다 실행부가 태그를 순차적으로 접근하며 다양한 이벤트 발생
//			   이벤트마다 개발자는 적절한 처리 진행

public class ParseMain {

	public static void main(String[] args) {
		// 자바의 파싱 기법 중 SAX 파싱을 위한 객체인 SAXParser 객체 예제
		// SAXParser는 SAXParserFactory라는 객체를 통해 인스턴스를 얻어와야 함
		
		// 새 SAXParserFactory 인스턴스 얻기
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		// xml의 위치를 윈도우 기반의 경로가 아닌, classpath 기준으로 가져와 보기
		// 추후 해당 프로그램을 다른 운영체제에서 실행 가능
		URL url = ClassLoader.getSystemResource("member.xml"); // 패키지 내 res 디렉토리에 넣어놨기 때문에 가능함
		// url은 자원의 위치를 의미하는 객체임
		// System.out.println(url);
		
		URI uri = null;
		try {
			uri = url.toURI();
			// System.out.println(uri.toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		// 파싱을 담당하는 객체인 SAXParser 얻기
		MyHandler handler = null;
		try {
			SAXParser parser = factory.newSAXParser();     // Parser의 인스턴스 얻기
			parser.parse(uri.toString(), handler = new MyHandler()); // uri, dh
			
			for(Member member : handler.list) {
				System.out.println("empno: "+member.getEmpno()
				+", ename: "+member.getEname()
				+", sal: "+member.getSal());
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
