package com.yg.portfolio.repository;


import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.User;


@Mapper
public interface CommunityRepository {
	
	public void notice();

	public void qna();
	
}
