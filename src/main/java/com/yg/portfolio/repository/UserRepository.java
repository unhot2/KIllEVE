package com.yg.portfolio.repository;


import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.User;


@Mapper
public interface UserRepository {
	
	public User findByUsername(String username);
	
	int save (User user);
}
