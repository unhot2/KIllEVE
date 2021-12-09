package com.yg.portfolio.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.RequestPath;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.yg.portfolio.model.PrincipalDetails;
import com.yg.portfolio.model.User;
import com.yg.portfolio.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	// 로그인 폼
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {
		model.addAttribute("error", error);
		return "/users/loginForm";
	}

	// 로그인 성공 메소드
	// 로그인 성공시 세션에 저장되어 있는 User값은 2가지 방법으로 가져온다.
	// 1. Authentication을 통해 principalDetails로 다운캐스팅하여 가져오는 방법
	// 2. @AuthenticationPrincipal 어노테이션을 통해 바로 PrincipalDetails에서 User를 가져오는 방법
	@GetMapping("/loginSucess")
	public String loginSucess(@AuthenticationPrincipal PrincipalDetails principalDetailss, HttpSession session) {
//		if (principalDetailss.getUser().getProvider() == null) {
//			System.out.println("★★★★★ 일반 로그인 ★★★★★	");
//		} else {
//			System.out.println("★★★★★ " + principalDetailss.getUser().getProvider() + " OAuth 로그인 ★★★★★	");
//		}
//		System.out.println("★★★★★ UserId		값 : " + principalDetailss.getUser().getUserId());
//		System.out.println("★★★★★ Password		값 : " + principalDetailss.getUser().getPassword());
//		System.out.println("★★★★★ UserName		값 : " + principalDetailss.getUser().getUserName());
//		System.out.println("★★★★★ Email		값 : " + principalDetailss.getUser().getEmail());
//		System.out.println("★★★★★ Role		값 : " + principalDetailss.getUser().getRole());
//		System.out.println("★★★★★ EmailRec		값 : " + principalDetailss.getUser().getEmailReceiveYn());
//		System.out.println("★★★★★ smsRec		값 : " + principalDetailss.getUser().getSmsReceiveYn());
//		System.out.println("★★★★★ Address		값 : " + principalDetailss.getUser().getAddress());
//		System.out.println("★★★★★ DetailAddress	값 : " + principalDetailss.getUser().getDetailAddress());
//		System.out.println("★★★★★ ZipCode		값 : " + principalDetailss.getUser().getZipCode());
//		System.out.println("★★★★★ Gender		값 : " + principalDetailss.getUser().getGender());
//		System.out.println("★★★★★ Phone		값 : " + principalDetailss.getUser().getPhone());
//		System.out.println("★★★★★ Provider		값 : " + principalDetailss.getUser().getProvider());
//		System.out.println("★★★★★ ProviderId	값 : " + principalDetailss.getUser().getProviderId());
		session.setAttribute("userRole", principalDetailss.getUser().getRole());
		session.setAttribute("userId", principalDetailss.getUser().getUserId());
		session.setAttribute("userName", principalDetailss.getUser().getUserName());
		return "redirect:/";
	}

	// 회원가입 폼
	@GetMapping("/joinForm")
	public String joinForm(@RequestParam(value = "nomalJoin", required = false) String nomalJoin, Model model) {
		if (nomalJoin != null) {
			model.addAttribute("nomalJoin", "Y");
		}
		return "/users/joinForm";
	}

	// 회원가입 => 아이디 중복체크
	@GetMapping("/findId")
	public @ResponseBody int findId(@RequestParam(value = "userId", required = false) String userId) {
		int cnt = userService.findId(userId);
		if (cnt >= 1) {
			return 1;
		} else {
			return 0;
		}
	}

	// 회원가입
	@PostMapping("/join")
	public String join(User user) {
		System.out.println("들어온값 userid : " + user.getUserId());
		System.out.println("들어온값 password : " + user.getPassword());
		System.out.println("들어온값 username: " + user.getUserName());
		System.out.println("들어온값 email : " + user.getEmail());
		System.out.println("들어온값 email제공자 : " + user.getEmailProvider());
		System.out.println("들어온값 phone : " + user.getPhone());
		System.out.println("들어온값 gender : " + user.getGender());
		System.out.println("들어온값 email yn : " + user.getEmailReceiveYn());
		System.out.println("들어온값 sms yn :" + user.getSmsReceiveYn());
		System.out.println("들어온값 zipcode :" + user.getZipCode());
		System.out.println("들어온값 address :" + user.getAddress());
		System.out.println("들어온값 detailAddress :" + user.getDetailAddress());
		System.out.println("들어온값 birthYear :" + user.getBirthYear());
		System.out.println("들어온값 birthMonth :" + user.getBirthMonth());
		System.out.println("들어온값 birthDay :" + user.getBirthDay());
		userService.join(user);
		return "redirect:/users/loginForm";
	}
}
