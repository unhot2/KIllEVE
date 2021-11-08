package com.yg.portfolio.repository;


import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.User;


@Mapper
public interface UserRepository {
	// 로그인
	public User findByUsername(String username);
	
	// 회원가입
	public int join (User user);
}
