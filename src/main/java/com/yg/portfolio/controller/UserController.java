package com.yg.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

	/*
	 * @GetMapping("/test/oauth/login") public @ResponseBody String
	 * loginOAuthTest(Authentication authentication,
	 * 
	 * @AuthenticationPrincipal OAuth2User oauth) {
	 * System.out.println("/test/oauth/login ================="); OAuth2User
	 * oAuth2User = (OAuth2User) authentication.getPrincipal();
	 * System.out.println("authentication : " + oAuth2User.getAttributes());
	 * System.out.println("oauth2User : " + oauth.getAttributes()); return
	 * "OAuth 세션 정보 확인하기"; }
	 * 
	 * @GetMapping("/test/login") public @ResponseBody String
	 * loginTest(Authentication authentication,
	 * 
	 * @AuthenticationPrincipal PrincipalDetails userDetails) {
	 * System.out.println("/test/login ================="); PrincipalDetails
	 * principalDetails = (PrincipalDetails) authentication.getPrincipal();
	 * System.out.println("authentication : " + principalDetails.getUser());
	 * System.out.println("userDetails : " + userDetails.getUser()); return
	 * "세션정보확인하기"; }
	 */

	@Autowired
	private UserService userService;

	// 로그인 폼
	@GetMapping("/loginForm")
	public String login() {
		return "/users/loginForm";
	}

	// 로그인 실패 메소드 (보류)
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

	// 로그인 성공 메소드
	// 로그인 성공시 세션에 저장되어 있는 User값은 2가지 방법으로 가져온다.
	// 1. Authentication을 통해 principalDetails로 다운캐스팅하여 가져오는 방법
	// 2. @AuthenticationPrincipal 어노테이션을 통해 바로 PrincipalDetails에서 User를 가져오는 방법
	@GetMapping("/loginSucess")
	public String loginSucess(@AuthenticationPrincipal PrincipalDetails principalDetailss) {
		System.out.println("");
		if (principalDetailss.getUser().getProvider() == null) {
			System.out.println("★★★★★ 일반 로그인 ★★★★★	" );
		} else {
			System.out.println("★★★★★ "+principalDetailss.getUser().getProvider()+" OAuth 로그인 ★★★★★	" );
		}
		System.out.println("★★★★★ UserId		값 : " + principalDetailss.getUser().getUserId());
		System.out.println("★★★★★ Password		값 : " + principalDetailss.getUser().getPassword());
		System.out.println("★★★★★ UserName		값 : " + principalDetailss.getUser().getUserName());
		System.out.println("★★★★★ Email		값 : " + principalDetailss.getUser().getEmail());
		System.out.println("★★★★★ Role		값 : " + principalDetailss.getUser().getRole());
		System.out.println("★★★★★ EmailRec		값 : " + principalDetailss.getUser().getEmailReceiveYn());
		System.out.println("★★★★★ smsRec		값 : " + principalDetailss.getUser().getSmsReceiveYn());
		System.out.println("★★★★★ Address		값 : " + principalDetailss.getUser().getAddress());
		System.out.println("★★★★★ DetailAddress	값 : " + principalDetailss.getUser().getDetailAddress());
		System.out.println("★★★★★ ZipCode		값 : " + principalDetailss.getUser().getZipCode());
		System.out.println("★★★★★ Gender		값 : " + principalDetailss.getUser().getGender());
		System.out.println("★★★★★ Phone		값 : " + principalDetailss.getUser().getPhone());
		System.out.println("★★★★★ Provider		값 : " + principalDetailss.getUser().getProvider());
		System.out.println("★★★★★ ProviderId	값 : " + principalDetailss.getUser().getProviderId());
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
		System.out.println("들어온값 userid : " + user.getUserId());
		System.out.println("들어온값 password : " + user.getPassword());
		System.out.println("들어온값 username: " + user.getUserName());
		System.out.println("들어온값 email : " + user.getEmail());
		System.out.println("들어온값 phone : " + user.getPhone());
		System.out.println("들어온값 gender : " + user.getGender());
		System.out.println("들어온값 email yn : " + user.getEmailReceiveYn());
		System.out.println("들어온값 sms yn :" + user.getSmsReceiveYn());
		System.out.println("들어온값 zipcode :" + user.getZipCode());
		System.out.println("들어온값 address :" + user.getAddress());
		System.out.println("들어온값 detailAddress :" + user.getDetailAddress());
		userService.join(user);
		return "redirect:/users/loginForm";
	}
}
