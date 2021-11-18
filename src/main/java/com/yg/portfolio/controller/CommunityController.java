package com.yg.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yg.portfolio.service.CommunityService;

@Controller
@RequestMapping("/community")
public class CommunityController {

	@Autowired
	private CommunityService communityService;

	@GetMapping("/notice")
	public String notice() {
		System.out.println("notice들어옴");
//		communityService.notice();
		return "/community/notice";
	}
	
	@GetMapping("/qna")
	public String qna() {
		System.out.println("qna들어옴");
//		communityService.qna();
		return "/community/qna";
	}

}
