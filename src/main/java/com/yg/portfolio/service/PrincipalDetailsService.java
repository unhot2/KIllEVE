package com.yg.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.yg.portfolio.config.PrincipalDetails;
import com.yg.portfolio.model.User;
import com.yg.portfolio.repository.UserRepository;


// 시큐리티 설정에서 loginProcessingURL("/login")
// login 요청이 들어오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	// 파라매터 user네임은 loginForm에서 input에 name이 username이어야 인식이 됨
	// 만약 바꾸려면 SecurityConfig에서 .usernameParameter("username2")와 같이 추가해주어야 함
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		if (userEntity != null) {
			// 시큐리티 session = Authentication = UserDetails
			// UserDetails 가 리턴되면 Authentication 내부에 들어가고 그 Authentication은 시큐리티 session내부에 들어감
			return new PrincipalDetails(userEntity);
		}
		else {
			System.out.println("★★★★★★★★★★★★★★★★★★★★★★★아이디 없음★★★★★★★★★★★★★★★★★★★★★★★");
			return null;
		}
	}

}
