package com.yg.portfolio.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yg.portfolio.model.User;


public class PrincipalDetails implements UserDetails{

	private User user;
	
	public PrincipalDetails(User user) {
		this.user = user;
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
		return user.getUsername();
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

}
