package com.yg.portfolio.config;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


// 시큐리티 로그인실패 핸들러
@Service
public class AuthFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException accessException) throws IOException, ServletException {
		Model model = null;
		model.addAttribute("error","존재하지 않는 사용자입니다.");
		if (accessException instanceof AuthenticationServiceException) {
			request.setAttribute("error", "존재하지 않는 사용자입니다.");
			System.out.println("존재x");
		
		} else if(accessException instanceof BadCredentialsException) {
			request.setAttribute("error", "비밀번호가 틀립니다.");
			System.out.println("비번x");
			
		} else if(accessException instanceof LockedException) {
			request.setAttribute("error", "잠긴 계정입니다..");
			
		} else if(accessException instanceof DisabledException) {
			request.setAttribute("error", "비활성화된 계정입니다..");
			
		} else if(accessException instanceof AccountExpiredException) {
			request.setAttribute("error", "만료된 계정입니다..");
			
		} else if(accessException instanceof CredentialsExpiredException) {
			request.setAttribute("error", "비밀번호가 만료되었습니다.");
		}
		response.sendRedirect("/users/error");
	}

}
