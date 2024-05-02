package com.sds.movieapp.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	private Member member;
	
	public CustomUserDetails(Member member) {
		// TODO Auto-generated constructor stub
		this.member = member;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> authList = new ArrayList();
		
		authList.add(new GrantedAuthority() {	
			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return member.getRole().getRole_name(); // 홈페이지 회원이라면 user
			}
		});
		
		return authList;
	}

	@Override
	public String getPassword() {
		return member.getMemberDetail().getPassword();
	}

	@Override
	public String getUsername() {
		return member.getNickname();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
