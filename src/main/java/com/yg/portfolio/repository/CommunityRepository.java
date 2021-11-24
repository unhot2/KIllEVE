package com.yg.portfolio.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.Notice;
import com.yg.portfolio.model.Qna;
import com.yg.portfolio.model.User;


@Mapper
public interface CommunityRepository {
	
	// 공지사항 목록 조회
	public List<Notice> notice(int startPage, int endPage);
	
	// 공지사항 검색결과 조회
	public List<Notice> noticeSearch(int startPage, int endPage, String search);
	
	// 공지사항 글개수 조회
	public int noticeCnt();
	
	// 공지사항 검색결과 개수 조회
	public int noticeSearchCnt(String search);
	
	// 공지사항 상세정보 조회
	public Notice noticeDetail(int boardNum);
	
	
	// QNA 목록 조회
	public List<Qna> qna(int startPage, int endPage);
	
	// QNA 글개수 조회
	public int qnaCnt();
	
	// QNA 글작성
	public int writeQna(Qna qna);
	
	// QNA 상세정보 조회
	public Qna qnaDetail(int boardNum);
		
	// 조회수 더하기
	public void noticeCntUp(int boardNum);
	
	// 조회수 더하기
	public void qnaCntUp(int boardNum);
	
}
