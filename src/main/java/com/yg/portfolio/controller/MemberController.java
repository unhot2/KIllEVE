package com.yg.portfolio.controller;

import javax.annotation.processing.SupportedSourceVersion;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yg.portfolio.model.User;
import com.yg.portfolio.service.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {

	@Autowired
	private MemberService memberService;

	// 마이페이지
	@GetMapping("/myPage")
	public String myPage(Model model) {
		return "/members/myPage";
	}
	
	// 회원정보 조회
	@GetMapping("/memberInfo")
	public String memberInfo(Model model, User user) {
		System.out.println("memberInfo method 들어옴 ---------------------------------------------");
		User info = memberService.memberInfo(user);
		System.out.println("userId 정보 :"+info.getUserId());
		System.out.println("password 정보 :"+info.getPassword());
		System.out.println("username 정보 :"+info.getUserName());
		System.out.println("email 정보 :"+info.getEmail());
		System.out.println("email제공자 정보 :"+info.getEmailProvider());
		System.out.println("phone 정보 :"+info.getPhone());
		System.out.println("gender 정보 :"+info.getGender());
		System.out.println("email Yn 정보 :"+info.getEmailReceiveYn());
		System.out.println("sms Yn 정보 :"+info.getSmsReceiveYn());
		System.out.println("zipcode 정보 :"+info.getZipCode());
		System.out.println("address 정보 :"+info.getAddress());
		System.out.println("detailAdd 정보 :"+info.getDetailAddress());
		System.out.println("role 정보 :"+info.getRole());
		System.out.println("birthYear 정보 :"+info.getBirthYear());
		System.out.println("birthMonth 정보 :"+info.getBirthMonth());
		System.out.println("birthDay 정보 :"+info.getBirthDay());
		model.addAttribute("userInfo",info);
		return "/members/memberInfo";
	}
	
	// 회원정보 수정
	@PostMapping("/memberModify")
	public String memberModify(User user, HttpSession session) {
		System.out.println("회원정보수정 들어옴 ---------------------------------------------");
		System.out.println("userId 정보 :"+user.getUserId());
		System.out.println("password 정보 :"+user.getPassword());
		System.out.println("username 정보 :"+user.getUserName());
		System.out.println("email 정보 :"+user.getEmail());
		System.out.println("phone 정보 :"+user.getPhone());
		System.out.println("gender 정보 :"+user.getGender());
		System.out.println("email Yn 정보 :"+user.getEmailReceiveYn());
		System.out.println("sms Yn 정보 :"+user.getSmsReceiveYn());
		System.out.println("zipcode 정보 :"+user.getZipCode());
		System.out.println("address 정보 :"+user.getAddress());
		System.out.println("detailAdd 정보 :"+user.getDetailAddress());
		System.out.println("role 정보 :"+user.getRole());
		System.out.println("birthYear 정보 :"+user.getBirthYear());
		System.out.println("birthMonth 정보 :"+user.getBirthMonth());
		System.out.println("birthDay 정보 :"+user.getBirthDay());
		memberService.memberModify(user);
		session.removeAttribute("userName");
		session.setAttribute("userName", user.getUserName());
		return "redirect:/";
	}
	
	// 회원탈퇴 
	@GetMapping("memberWithdrawal")
	public String memberWithdrawal(User user) {
		memberService.memberWithdrawal(user);
		return "redirect:/";
	}

}
