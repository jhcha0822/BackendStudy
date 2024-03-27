package com.sds.asyncboard.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

// 목록 요청을 처리하는 서블릿
public class ListServlet extends HttpServlet{
	
	BoardDAO boardDAO = new BoardDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter(); 
		
		// JSON으로 보내주기
		List boardList = boardDAO.selectAll();
		
		// Gson 라이브러리를 이용하면 자바객체와 JSON 스트링과의 변환을 자유롭게 진행할 수 있다
		Gson gson = new Gson();
		String json = gson.toJson(boardList);
		
		out.println(json);
		
	}
}
