package com.yg.portfolio.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yg.portfolio.model.Notice;
import com.yg.portfolio.model.Qna;
import com.yg.portfolio.service.CommunityService;

@Controller
@RequestMapping("/community")
public class CommunityController {

	@Autowired
	private CommunityService communityService;

	@GetMapping("/notice")
	public String notice(Model model, 
			@RequestParam(value = "search", required = false) String search, 
			@RequestParam(value = "currentPage", required = false) Integer currentPage) {
		if (search == null || search.isEmpty()) {
			List<Notice> list = communityService.notice(currentPage, model);
			model.addAttribute("list",list);
		} else {
			List<Notice> list = communityService.noticeSearch(currentPage, model, search);
			model.addAttribute("list",list);
		}
		return "/community/notice";
	}
	
	@GetMapping("/notice/{boardNum}")
	public String noticeDetail(Model model, @PathVariable int boardNum) {
		model.addAttribute("detail",communityService.noticeDetail(boardNum));
		return "/community/noticeDetailForm";
	}
	
	
	@GetMapping("/qna")
	public String qna(Model model, 
			@RequestParam(value = "search", required = false) String search, 
			@RequestParam(value = "currentPage", required = false) Integer currentPage) {
		if (search == null || search.isEmpty()) {
			List<Qna> list = communityService.qna(currentPage, model);
			model.addAttribute("list",list);
		} 
//		else {
//			List<Notice> list = communityService.qnaSearch(currentPage, model, search);
//			model.addAttribute("list",list);
//		}
		return "/community/qna";
	}
	
	@GetMapping("/qna/{boardNum}")
	public String qnaDetail(Model model, @PathVariable int boardNum,
			@RequestParam(value = "chkSecret", required = false) String chkSecret) {
		if (chkSecret.equals("secret")) {
			model.addAttribute("boardNum",boardNum);
			return "/community/qnaCheckPw";
		} else {
			model.addAttribute("detail",communityService.qnaDetail(boardNum));
			return "/community/qnaDetailForm";
		}
	}
	
	// QNA 글 작성 Form 이동 
	@GetMapping("/qnaForm")
	public String noticeForm(@RequestParam(value = "boardNum", required = false) Integer boardNum, Model model) {
		if (boardNum != null) {
			model.addAttribute("detail",communityService.noticeDetail(boardNum));
		}
		return "/community/qnaForm";
	}
	
	@GetMapping("/chkPassword")
	public @ResponseBody int chkPassword(Model model,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "boardNum", required = false) Integer boardNum,
			@RequestParam(value = "role", required = false) String role) {
		System.out.println("role 값 : "+role);
		Qna qna = communityService.qnaDetail(boardNum);
		if (role.equals("ROLE_ADMIN") || role.equals("ROLE_MANAGER")) {
			return 1;
		} 
		else {
			if (qna.getPassword().equals(password)) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	// QNA 글 작성
	@PostMapping("/writeQna")
	public String writeQna(Qna qna) {
		communityService.writeQna(qna);
		return "redirect:/community/qna";
	}

}
