package com.yg.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

	// 로그인
	@GetMapping("/login")
	public String login() {
		return "users/login"; 
	}

	// 회원가입
	@GetMapping("/join")
	public String join() {
		return "users/joinForm";
	}
}
