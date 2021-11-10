package com.yg.portfolio.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yg.portfolio.model.User;
import com.yg.portfolio.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// 로그인 폼
	@GetMapping("/loginForm")
	public String login() {
		return "/users/loginForm"; 
	}
	
	// 로그인 폼
	@GetMapping("/loginFail")
	public @ResponseBody String loginFail(Model model) {
		/*
		 * if (accessException instanceof AuthenticationServiceException) {
		 * model.addAttribute("error", "존재하지 않는 사용자입니다."); System.out.println("존재x");
		 * 
		 * } else if(accessException instanceof BadCredentialsException) {
		 * model.addAttribute("error", "비밀번호가 틀립니다."); System.out.println("비번x");
		 * 
		 * } else if(accessException instanceof LockedException) {
		 * model.addAttribute("error", "잠긴 계정입니다..");
		 * 
		 * 
		 * } else if(accessException instanceof DisabledException) {
		 * model.addAttribute("error", "비활성화된 계정입니다..");
		 * 
		 * } else if(accessException instanceof AccountExpiredException) {
		 * model.addAttribute("error", "만료된 계정입니다..");
		 * 
		 * } else if(accessException instanceof CredentialsExpiredException) {
		 * model.addAttribute("error", "비밀번호가 만료되었습니다."); }
		 */
		return "/users/loginFail"; 
	}
	
	// 로그인 성공
	@GetMapping("/loginSucess")
	public String loginSucess(Authentication authentication, HttpSession session) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.print("★★★★★★★★★★★★★ 로그인성공 ID : ");
		System.out.println(userDetails.getUsername()+" ★★★★★★★★★★★★★★★★★★★");
		session.setAttribute("loginName", userDetails.getUsername());
		return "redirect:/"; 
	}

	// 회원가입 폼
	@GetMapping("/joinForm")
	public String joinForm() {
		return "/users/joinForm";
	}
	
	// 회원가입
	@PostMapping("/join")
	public String join(User user) {
		System.out.println("들어온값 userid : "+user.getUserId());
		System.out.println("들어온값 password : "+user.getPassword());
		System.out.println("들어온값 username: "+user.getUserName());
		System.out.println("들어온값 email : "+user.getEmail());
		System.out.println("들어온값 phone : "+user.getPhone());
		System.out.println("들어온값 gender : "+user.getGender());
		System.out.println("들어온값 email yn : "+user.getEmailReceiveYn());
		System.out.println("들어온값 sms yn :"+user.getSmsReceiveYn());
		System.out.println("들어온값 zipcode :"+user.getZipCode());
		System.out.println("들어온값 address :"+user.getAddress());
		System.out.println("들어온값 detailAddress :"+user.getDetailAddress());
		userService.join(user);
		return "redirect:/users/loginForm";
	}
}
