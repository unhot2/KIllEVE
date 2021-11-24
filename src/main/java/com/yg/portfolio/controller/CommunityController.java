package com.yg.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String qnaDetail(Model model, @PathVariable int boardNum) {
		model.addAttribute("detail",communityService.qnaDetail(boardNum));
		return "/community/qnaDetailForm";
	}
	
	// QNA 글 작성 Form 이동
	@GetMapping("/qnaForm")
	public String noticeForm(@RequestParam(value = "boardNum", required = false) Integer boardNum, Model model) {
		if (boardNum != null) {
			model.addAttribute("detail",communityService.noticeDetail(boardNum));
		}
		return "/community/qnaForm";
	}
	
	// QNA 글 작성
	@PostMapping("/writeQna")
	public String writeQna(Qna qna) {
		communityService.writeQna(qna);
		return "redirect:/community/qna";
	}

}
