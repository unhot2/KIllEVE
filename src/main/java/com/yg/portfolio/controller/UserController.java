package com.yg.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yg.portfolio.dao.MemberMapper;
import com.yg.portfolio.dto.CsrfVO;
import com.yg.portfolio.dto.UserPrincipalVO;
import com.yg.portfolio.service.SecurityService;


@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	MemberMapper mm;
	
	@Autowired
	SecurityService hs;
	
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
	
	@GetMapping("/")
	public String loadExceptionPage(ModelMap model) throws Exception{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipalVO userPrincipalVO = (UserPrincipalVO) auth.getPrincipal();//유저 권한 가져오기
		
		model.addAttribute("name",userPrincipalVO.getUsername());
		model.addAttribute("auth",userPrincipalVO.getAuthorities());
	
		return "index";
	}
	
	@GetMapping("/access-denied")
	public String loadAccessdeniedPage() throws Exception{
		return "error";
	}
	
	@PostMapping("/csrf/form")
	public String csrfFromSubmit(@ModelAttribute CsrfVO csrfVO, ModelMap model){
			
		model.addAttribute("name",csrfVO.getName());
		
		return "result";
	}
	
	@PostMapping("/csrf/ajax")
	public @ResponseBody CsrfVO csrfAJAXSubmit(@RequestBody CsrfVO csrfVO) {
		
		return csrfVO;
	}
	
	//contentType application/x-www-form-urlencoded 일떄는 @RequestBody 쓰지 않음
	@PostMapping("/csrf/ajaxForm")
	public @ResponseBody CsrfVO AJAXFormSubmit(CsrfVO csrfVO) {
		
		return csrfVO;
	}
}
