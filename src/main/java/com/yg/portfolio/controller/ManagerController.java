package com.yg.portfolio.controller;

import java.util.List;
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
	
	@GetMapping("/managerMenu")
	public String productForm() {
		return "/manager/managerMenu";
	}
	
	// 상품 등록
	@PostMapping("/productSave")
	public String productSave(@RequestParam(value = "files[]", required = false) List files	
			, @RequestParam(value= "colorList[]", required = false) List colorList
			, @RequestParam(value= "sizeList[]", required = false) List sizeList
			, Product product) throws Exception {
			System.err.println("======================== 상품 등록 넘어온 값 ========================");
			System.out.println("상품명 : "+product.getProductName());
			System.out.println("카테고리 : "+product.getCategory());
			System.out.println("할인율 : "+product.getDiscountRate());
			System.out.println("소비자가 : "+product.getConsumerPrice());
			System.out.println("판매가 : "+product.getSalePrice());
			System.out.println("재고 : "+product.getStock());
			System.out.println("대표이미지 명 : "+product.getMainImage());
			System.out.println("hot	: "+product.getHotYn());
			System.out.println("mdpick	: "+product.getMdPickYn());
			System.out.println("quick	: "+product.getQuickYn());
			System.out.println("best	: "+product.getBestYn());
			System.out.println("sale	: "+product.getSaleYn());
			if(colorList.size() > 0) {
				 for(Object color : colorList) { 
					  System.out.println("색상 명  :"+color);
				  }
			}
			if(sizeList.size() > 0) {
				for(Object size : sizeList) { 
					System.out.println("사이즈 명  :"+size);
				}
			}
			if(files.size() > 0) {
				for(Object file : files) { 
					System.out.println("상세 이미지 명  :"+file);
				}
			}
			managerService.productSave(product);
			for(int i =0; i < colorList.size(); i++) { 
				managerService.productColorSave((String) colorList.get(i));
			}
			for(int i =0; i < sizeList.size(); i++) { 
				managerService.productSizeSave((String) sizeList.get(i));
			}
			for(int i =0; i < files.size(); i++) { 
				managerService.productImgSave((String) files.get(i));
			}
	    return "redirect:/";
	}
}
