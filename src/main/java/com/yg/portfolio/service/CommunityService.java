package com.yg.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.yg.portfolio.model.Notice;
import com.yg.portfolio.model.Qna;
import com.yg.portfolio.repository.CommunityRepository;

@Service
public class CommunityService {

	@Autowired
	private CommunityRepository communityRepository;
	
	int totalCount = 0;		// 총 게시물 수
	int listCount = 10;		// 한 페이지에 보여줄 게시물 수
	int totalPage = 0;		// 총 페이지 수
	int currentPage = 0;	// 현재 페이지
	int startPage = 0;		// 시작 게시물 번호
	int endPage = 0;		// 끝 게시물 번호
	
	public List<Notice> notice(Integer page, Model model) {
		totalCount = communityRepository.noticeCnt(); // 공지사항 총 게시물 수 
		paging(page); //페이징 처리
		List<Notice> noticeList = communityRepository.notice(startPage, endPage);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		return noticeList;
	}
	
	public List<Notice> noticeSearch(Integer page, Model model, String search) {
		totalCount = communityRepository.noticeSearchCnt(search); // 공지사항 검색된 게시물 수
		paging(page); //페이징 처리
		List<Notice> noticeSearchList = communityRepository.noticeSearch(startPage, endPage, search);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", search);
		return noticeSearchList;
	}
	
	public Notice noticeDetail(int boardNum) {
		communityRepository.noticeCntUp(boardNum);
		return communityRepository.noticeDetail(boardNum);
	}
	
	public void paging(Integer page) {
		totalPage = totalCount / listCount + (totalCount % listCount > 0 ? 1 : 0);
		if (page == null) {
			currentPage = 1;
		}
		else {
			if (page > totalPage) {
				currentPage = totalPage;
			}
			else if (page < 1) {
				currentPage = 1;
			}
			else {
				currentPage = page;
			}
		}
		startPage = (listCount * (currentPage - 1)) + 1;
		endPage = startPage + listCount;
	}
	
	
	public List<Qna> qna(Integer page, Model model) {
		totalCount = communityRepository.qnaCnt(); // 공지사항 총 게시물 수 
		paging(page); //페이징 처리
		List<Qna> qnaList = communityRepository.qna(startPage, endPage);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		return qnaList;
	}
	
	public Qna qnaDetail(int boardNum) {
		communityRepository.qnaCntUp(boardNum);
		return communityRepository.qnaDetail(boardNum);
	}
	
	// 공지사항 글 작성
	public void writeQna(Qna qna) {
		int test = communityRepository.writeQna(qna);
		if (test == 1) {
			System.out.println("QNA 작성 성공 : "+test);
		}
		else {
			System.out.println("QNA 작성 실패 : "+test);
		}
	};
}
