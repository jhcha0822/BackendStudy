package com.sds.springproject.model.member;

import com.sds.springproject.domain.Bio;

// 모델 영역에서 DAO들에게 업무를 시킬 수 있는 서비스 객체
// 서비스가 존재하지 않는다면 이 역할을 하위 컨트롤러가 담당해야 하므로
// 컨트롤러가 모델의 역할까지 수행하게 되어 MVC패턴에 위배됨
// 컨트롤러(상담원)이 진두지휘 하는 꼴. 부장님을 두자
// 이 부장님의 주소를 전달해야 하기에 느슨하게(스프링) 보유해야 함: 인터페이스
public interface MemberService {
	
	// 회원 등록
	public int regist(Bio bio); // DTO중 bio(자식)가 member의 정보도 가지고 있기에 
	
	
}
