package com.sds.springproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.sds.springproject.domain.Bio;
import com.sds.springproject.domain.Member;
import com.sds.springproject.model.bio.BioDAO;
import com.sds.springproject.model.member.MemberDAO;
import com.sds.springproject.model.member.MemberService;
import com.sds.springproject.mybatis.MybatisManager;

// 회원 등록 요청을 처리하는 하위 컨트롤러
public class RegistController implements Controller{

	// 아래 두 DAO는 같은 트랜젝션으로 묶어야 하기에 DAO간 서로 SqlSession이 공유되어야 함
	// 따라서 SqlSession의 취득은 현재 하위 컨트롤러가 취득하여 같은 SqlSession을 DAO들의 insert()메서드의 매개변수로 넣어줌
	// SqlSession을 모아놓은 객체인 .SqlSessionFactory를 가진 객체 MybatisManager를 보유하도록 하자

	// 컨트롤러는 모델 영역의 자세한 업무를 부담해서는 안된다 (MVC)
//	private MybatisManager manager;
//	
//	public void setManager(MybatisManager manager) { // DI
//		this.manager = manager;
//	}
//	
//	// new 대신 DI
//	private MemberDAO memberDAO; // mybatisDAO hibernateDAO등 모두 가능
//	private BioDAO bioDAO;
//	
//	
//	public void setMemberDAO(MemberDAO memberDAO) {
//		// DI로 주입받으려면 setter 메서드를 준비해놓아야 함
//		this.memberDAO = memberDAO;
//	}
//	
//	public void setBioDAO(BioDAO bioDAO) {
//		this.bioDAO = bioDAO;
//	}
	
	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 알맞는 모델 객체에 업무 전달
		Member member = new Member(); // DTO, has-a로 되어 있는 1회성. 따라서 DI 필요없음
		member.setId(request.getParameter("id"));
		member.setPass(request.getParameter("pass"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		
		Bio bio = new Bio();
		bio.setBlood(request.getParameter("blood"));
		bio.setHeight(Float.parseFloat(request.getParameter("height")));
		bio.setWeight(Float.parseFloat(request.getParameter("weight")));
		bio.setMember(member);
		memberService.regist(bio); // 추상적으로 업무 지시
		
//		// DAO에게 일을 전달하기 전 SqlSession을 얻어와 분배
//		SqlSession sqlSession = manager.getSqlSession();
//		//System.out.println("insert 전: "+member.getMember_idx());		
//		int result = memberDAO.insert(sqlSession, member);
//		
//		// member 테이블에 insert 이후 pk에 키 값 반환
//		//System.out.println("insert 후: "+member.getMember_idx());
		
		int result = 0;
		result = memberService.regist(bio);
		
		ModelAndView mav = new ModelAndView();
		
		if(result > 0) {
			// member 신상정보가 등록되면 bio 정보도 insert
//			Bio bio = new Bio(); // empty 상태의 DTO 생성
//			bio.setBlood(request.getParameter("blood"));
//			bio.setHeight(Float.parseFloat(request.getParameter("height")));
//			bio.setWeight(Float.parseFloat(request.getParameter("weight")));
//			bio.setMember(member);
//			result = bioDAO.insert(sqlSession, bio);
//			
//			if(result > 0) { // 둘 다 성공 시
//				sqlSession.commit();
//			}
//			manager.release(sqlSession);
			
			// 요청을 끊고 회원 목록 페이지로 redirect
			mav.setViewName("redirect:/member/list");
			
		} else {
			// 에러 내용을 가져가기 위해 forwarding
			mav.addObject("msg", "회원 가입 실패");
			mav.setViewName("member/error");
		}
		
		return mav;
	}

}
