package com.yg.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yg.portfolio.model.User;
import com.yg.portfolio.repository.UserRepository;


@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	
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

	// 회원가입 폼
	@GetMapping("/joinForm")
	public String joinForm() {
		System.out.println("회원가입폼 들어옴");
		return "/users/joinForm";
	}
	
	// 회원가입
	@PostMapping("/join")
	public String join(User user) {
		user.setRole("ROLE_USER");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
		if (userRepository.save(user) == 1) {
			System.out.println("회원가입 성공");
		}
		return "redirect:/loginForm";
	}
}
