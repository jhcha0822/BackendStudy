<%@page import="com.sds.newsapp.map.StoreDAO"%>
<%@page import="com.sds.newsapp.map.Store"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%!
	// jsp가 서블릿으로 변경될 때 멤버영역으로 전환
	StoreDAO storeDAO = new StoreDAO();
%>
<%
	// 클라이언트가 비동기 요청을 시도한다는 것은 현재 클라이언트가 보고 있는 페이지를 새로고침 하기를 원하지 않는 것
	// 완전한 html 문서로 응답을 할 경우 리다이렉트됨
	// 순수 data만을 보내야 백그라운드 처리가 가능
	// 현재 jsp는 작성의 역할을 수행해야 하므로 등록 성공여부를 반환
	
	request.setCharacterEncoding("utf-8"); // 파라미터의 인코딩 처리
	
	String lati = request.getParameter("lati");
	String longi = request.getParameter("longi");
	String name = request.getParameter("name");
	
	Store store = new Store();
	
	store.setLati(Double.parseDouble(lati));
	store.setLongi(Double.parseDouble(longi));
	store.setName(name);
	
	int result = storeDAO.insert(store);
	
	StringBuilder sb = new StringBuilder();
	sb.append("{");
	sb.append("\"result\":\"ok\"");
	sb.append("}");
	
	out.print(sb.toString()); // 결과를 JSON 문자열로 보낸다
	
	//if(result>0)
	//	out.print("Success");
	//else
	//	out.print("Fail");
	
	
	
%>