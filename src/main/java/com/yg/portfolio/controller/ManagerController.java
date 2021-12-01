package com.yg.portfolio.controller;

import java.io.File;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yg.portfolio.model.Notice;
import com.yg.portfolio.model.Product;
import com.yg.portfolio.model.Qna;
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
	
	// 공지사항 글 삭제
	@PostMapping("/deleteNotice")
	public @ResponseBody int deleteNotice(@RequestParam(value = "boardNum", required = false) Integer boardNum) {
		return managerService.deleteNotice(boardNum);
	}
	
	// QNA 답글 페이지 이동
	@GetMapping("/qnaReplyForm")
	public String qnaReplyForm(@RequestParam(value = "boardNum", required = false) Integer boardNum,Model model) {
		model.addAttribute("detail",communityService.qnaDetail(boardNum));
		return "/manager/qnaReplyForm";
	}
	
	// QNA 답글 작성
	@PostMapping("/writeReplyQna")
	public String writeReplyQna(Qna qna) {
		System.out.println("답변작성 컨트롤러");
		managerService.writeReplyQna(qna);
		return "redirect:/community/qna";
	}
	
	// 상품 등록 폼
	@GetMapping("/productForm")
	public String productForm() {
		return "/manager/productForm";
	}
	
	// 상품 등록
	@PostMapping("/productSave")
	public String productSave(@RequestParam("files") List<MultipartFile> files
			, Product product) throws Exception {
			System.out.println(product.getProductName());
//		  String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
//		  System.out.println("rootPath 값 : "+rootPath);
//		  String basePath = rootPath + "/" + "multi"; // 파일 업로드(여러개) 처리 부분 
		  for(MultipartFile file : files) { 
			  System.out.println("file값  :"+file.getOriginalFilename());
//			  String originalName = file.getOriginalFilename(); 
//			  String filePath = basePath + "/" + originalName; 
//			  File dest = new File(filePath);
//			  file.transferTo(dest); 
		  }
	    return "redirect:/";
	}
}
