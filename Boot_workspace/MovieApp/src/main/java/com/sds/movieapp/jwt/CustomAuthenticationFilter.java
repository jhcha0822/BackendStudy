package com.sds.movieapp.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sds.movieapp.domain.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

// 지금까지 Spring Security에서 자동으로 설정된 UsernamePasswordAuthenticationFilter를 이용하여
// 전통적인 세션방식의 로그인을 처리하였지만, 이 시점부터는 이 세션방식의 로그인을 이용하지 않기 위한 필터를 사용: UsernamePasswordAuthenticationFilter를 커스텀
// 이 클래스 이용이 JWT 이용을 의미하지는 않는다. 로그인하는 순간을 제어하는 클래스일 뿐임
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	// Authenticationmanager는 인터페이스, SpringSecurity 내부적으로 생성되어 처리되기에 개발자는 Spring으로부터 얻어와 사용
	private AuthenticationManager authenticationManager; // new 불가
	
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {//, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.authenticationManager = authenticationManager; 
//		this.bCryptPasswordEncoder = bCryptPasswordEncoder; // 암호화된 비밀번호 확인을 위함
	}
	
	@Autowired
	private JwtUtil jwtUtil;
	
	// 사용자가 로그인하려고 할 때
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		// 사용자가 로그인폼에서 전송한 아이디 패스워드가 이 메서드로 전달되는지 체크
		String username = this.obtainUsername(request);
		String password = this.obtainPassword(request);
		
		System.out.println("username : ============================"+username);
		System.out.println("password : ============================"+password);
//		log.debug("username : ============================"+username);
//		log.debug("password : ============================"+password);
		
//		log.debug(bCryptPasswordEncoder.encode(password));
		
		// 유저명과 비밀번호를 UsernamePasswordAuthenticationFilter 객체에 담아 놓고
		// SpringSecurity로 하여금 DB를 연동하여 회원정보를 인증
		// CustomerUserDetailsService 객체를 이용하여 db를 조회해주는 객체 : AuthenticationManager
//		Authentication auth = new UsernamePasswordAuthenticationToken(username, bCryptPasswordEncoder.encode(password));
//		return authenticationManager.authenticate(auth); // CustomUserDetailsService에서 호출
		Authentication auth=new UsernamePasswordAuthenticationToken(username, password, null);
		return authenticationManager.authenticate(auth); // CustomUserDetailsService의 db조회 메서드
	}
	
	// Spring Security가 무조건 username 파라미터를 찾으므로, 만일 개발자가 다른 이름의 파라미터를 이용하고 있다면 object~ 메서드를 재정의(오버라이드)
	@Override	
	protected String obtainUsername(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return request.getParameter("uid");
	}
	
	// 사용자 인증이 성공될 때
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();
		String username = customUserDetails.getUsername(); //uid  반환
		String role=customUserDetails.getMember().getRole().getRole_name();
		
		log.debug("회원정보가 존재합니다. 로그인 성공");
		
		//회원정보가 존재하므로, 세션 로그인 처리 대신 클라이언트에게 JWT 토큰을 발급해주자 
		long expireTime = (1*1000*60)*60; //60분
		String token=null;
		try {
			token = jwtUtil.generateToken(username, role, expireTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//생성된 결과물인 토큰을 클라이언트에게 전송하자!!
		//따라서 응답정보를 만들자...
		Map<String, Object> responseMap = new HashMap();
		responseMap.put("success", true);
		responseMap.put("token", token);
		
		response.setStatus(HttpServletResponse.SC_OK);//200
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream()  , responseMap);
	}
	
	// 사용자 인증이 실패할 때
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.debug("회원정보 없음, 로그인 실패하였습니다.");
	}
}
