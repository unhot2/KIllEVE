package com.yg.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yg.portfolio.model.Notice;
import com.yg.portfolio.service.CommunityService;
import com.yg.portfolio.service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private CommunityService communityService;
	
	// 공지사항 글 작성 Form 이동
	@GetMapping("/noticeForm")
	public String noticeForm(@RequestParam(value = "boardNum", required = false) Integer boardNum, Model model) {
		if (boardNum != null) {
			model.addAttribute("detail",communityService.noticeDetail(boardNum));
		}
		return "/manager/noticeForm";
	}
	
	// 공지사항 글 작성
	@PostMapping("/writeNotice")
	public String writeNotice(Notice notice) {
		System.out.println("제목 : "+notice.getTitle());
		System.out.println("작성자 : "+notice.getUserName());
		System.out.println("내용 : "+notice.getContent());
		System.out.println("작성자ID : "+notice.getUserId());
		managerService.writeNotice(notice);
		return "redirect:/community/notice";
	}
	
	// 공지사항 글 수정
	@PostMapping("/updateNotice")
	public String updateNotice(Notice notice) {
		System.out.println("수정 Controller");
		System.out.println("제목 : "+notice.getTitle());
		System.out.println("내용 : "+notice.getContent());
		System.out.println("작성자ID : "+notice.getUserId());
		System.out.println("번호 : "+notice.getBoardNum());
		managerService.updateNotice(notice);
		return "redirect:/community/notice";
	}

}
