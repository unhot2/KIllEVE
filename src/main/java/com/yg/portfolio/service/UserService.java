package com.yg.portfolio.service;

import javax.servlet.RequestDispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.yg.portfolio.model.PrincipalDetails;
import com.yg.portfolio.model.User;
import com.yg.portfolio.repository.UserRepository;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

// 시큐리티 설정에서 loginProcessingURL("/login")
// login 요청이 들어오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;

//	파라매터 user네임은 loginForm에서 input에 name이 username이어야 인식이 됨
//	만약 바꾸려면 SecurityConfig에서 .usernameParameter("username2")와 같이 추가해주어야 함
//	시큐리티 session = Authentication = UserDetails
// 	UserDetails 가 리턴되면 Authentication 내부에 들어가고 그 Authentication은 시큐리티 securityContextHolder에 들어감
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUserId(userId);
		if (userEntity == null) {
			throw new BadCredentialsException(String.format("아이디를 찾을 수 없음"));
		}
		else {
			return new PrincipalDetails(userEntity);
		}
	}
//	@Override
//	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//		User userEntity = userRepository.findByUserId(userId);
//		System.out.println("userEntity값 : "+userEntity);
//		if (userEntity == null) {
//			try {
//				throw new BadCredentialsException(String.format("아이디 또는 비밀번호를 확인해주세요"));
//			} catch (Exception e) {
//				System.out.println("에러메세지:"+e.getMessage());
//				return new PrincipalDetails(new User());
//			}
//		}
//		else {
//			return new PrincipalDetails(userEntity);
//		}
//	}
	

	// 회원가입시 ID에 따라 ROLE 다르게 설정
	public int join(User user) {
		if (("admin").equals(user.getUserId())) {
			user.setRole("ROLE_ADMIN");
		} else if (("manager").equals(user.getUserId())) {
			user.setRole("ROLE_MANAGER");
		} else {
			user.setRole("ROLE_USER");
		}
		// BCryptPasswordEncoder 통해 비밀번호 암호화
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.join(user);
	}

}
