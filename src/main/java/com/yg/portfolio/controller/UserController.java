package com.yg.portfolio.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		return "users/loginForm"; 
	}
	
	// 로그인 폼
	@GetMapping("/loginFail")
	public String loginFail() {
		return "users/loginFail"; 
	}
	
	// 로그인 성공
	@GetMapping("/loginSucess")
	public String loginSucess(Authentication authentication, HttpSession session) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.print("로그인한 사용자 : ");
		System.out.println(userDetails.getUsername());
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
		if (userService.join(user) == 1) {
			System.out.println("회원가입 성공");
		}
		return "redirect:/loginForm";
	}
}
