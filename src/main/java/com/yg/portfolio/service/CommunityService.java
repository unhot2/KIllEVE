package com.yg.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.portfolio.repository.CommunityRepository;

@Service
public class CommunityService {

	@Autowired
	private CommunityRepository communityRepository;
	
	public void notice() {
		communityRepository.notify();
	}
	
	public void qna() {
		communityRepository.qna();
	}
}
