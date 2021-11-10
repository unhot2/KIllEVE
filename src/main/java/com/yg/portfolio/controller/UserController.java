package com.yg.portfolio.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yg.portfolio.model.PrincipalDetails;
import com.yg.portfolio.model.User;
import com.yg.portfolio.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {
	
	
	@GetMapping("/test/oauth/login")
	public @ResponseBody String loginOAuthTest(Authentication authentication, @AuthenticationPrincipal OAuth2User oauth) {
		System.out.println("/test/oauth/login =================");
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		System.out.println("authentication : "+oAuth2User.getAttributes());
		System.out.println("oauth2User : "+oauth.getAttributes());
		return "OAuth 세션 정보 확인하기";
	}
	
	@GetMapping("/test/login")
	public @ResponseBody String loginTest(Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails) {
		System.out.println("/test/login =================");
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("authentication : "+principalDetails.getUser()); 
		System.out.println("userDetails : "+userDetails.getUser());
		return "세션정보확인하기";
	}
	
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
	public String loginSucess(Authentication authentication, HttpSession session, @AuthenticationPrincipal PrincipalDetails principalDetailss) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("principalDetailss 값 : "+principalDetailss.getUser());
		System.out.print("★★★★★★★★★★★★★ 로그인성공 ID : ");
		System.out.println(userDetails.getUsername()+" ★★★★★★★★★★★★★★★★★★★");
		session.setAttribute("loginName", userDetails.getUsername());
		System.out.println("로그인 후 넘어온 값 : "+principalDetails.getUser());
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
