package com.yg.portfolio.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	private User user;
	private Map<String,Object> attribute;
	
	// 일반로그인
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	// OAuth 로그인
	public PrincipalDetails(User user,Map<String,Object> attributes) {
		this.user = user;
		this.attribute = attributes;
	}
	
	// 해당 USER의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}
	
	// 계정만료 여부
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	// 계정잠금 여부
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 계정비밀번호 기간초과 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정활성화 여부
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attribute;
	}

	@Override
	public String getName() {
		return null;
	}

}
