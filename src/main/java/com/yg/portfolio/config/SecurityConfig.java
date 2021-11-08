package com.yg.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration //이 클래스를 통해 bean 등록이나 각종 설정을 하겠다는 표시


@EnableWebSecurity // Spring Security 설정할 클래스라고 정의합니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean //회원가입시 비번 암호화에 필요한 bean 등록
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //csrf 비활성화
		http.authorizeRequests() 
			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")// 매니저권한 필요
			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN')")// 매니저권한 필요
			.anyRequest().permitAll() // 그 외의 주소는 아무나 접근가능
		.and()
			.formLogin()
			.loginPage("/users/loginForm") //로그인 폼
			.loginProcessingUrl("/login") // LOGIN 주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행 ( 컨트롤러에 /login에 대한 메소드 안만들어도 됨 )
			.defaultSuccessUrl("/", true) /*로그인 성공시 url*/
			.failureUrl("/users/loginFail"); /*로그인 실패시 url*/
	}

}