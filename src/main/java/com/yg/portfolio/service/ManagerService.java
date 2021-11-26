package com.yg.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.portfolio.model.Notice;
import com.yg.portfolio.model.Qna;
import com.yg.portfolio.repository.ManagerRepository;

@Service
public class ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	// 공지사항 글 작성
	public void writeNotice(Notice notice) {
		int test = managerRepository.writeNotice(notice);
		if (test == 1) {
			System.out.println("NOTICE 작성 성공 : "+test);
		}
		else {
			System.out.println("NOTICE 작성 실패 : "+test);
		}
	};
	
	// 공지사항 글 수정
	public void updateNotice(Notice notice) {
		int test = managerRepository.updateNotice(notice);
		if (test == 1) {
			System.out.println("NOTICE 수정 성공 : "+test);
		}
		else {
			System.out.println("NOTICE 수정 실패 : "+test);
		}
	}
	
	// 공지사항 글 삭제
	public int deleteNotice(Integer boardNum) {
		return managerRepository.deleteNotice(boardNum);
	}
	
	// QNA 답글 작성
	public int writeReplyQna(Qna qna) {
		qna.setTitle("킬이브에서 답변드립니다");
		qna.setChkSecret("secret");
		return managerRepository.writeReplyQna(qna);
	};
}
