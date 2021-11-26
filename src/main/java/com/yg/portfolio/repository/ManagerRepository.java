package com.yg.portfolio.repository;


import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.Notice;
import com.yg.portfolio.model.Qna;


@Mapper
public interface ManagerRepository {
	
	// 공지사항 글 작성
	public int writeNotice(Notice notice);
	
	// 공지사항 글 수정
	public int updateNotice(Notice notice);
	
	// 공지사항 글 삭제
	public int deleteNotice(Integer boardNum);
	
	// QNA 답글 작성
	public int writeReplyQna(Qna qna);
	
}
