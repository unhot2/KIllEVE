package com.yg.portfolio.dao;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yg.portfolio.dto.UserVO;


@Repository
@Mapper
public interface MemberMapper {

	ArrayList<UserVO> findByUserId(@Param("id") String id);
	
	int userSave(UserVO userVO);
	
	int userRoleSave(@Param("userNo") String id,@Param("roleNo") int roleNo);
	
	int findUserNo(@Param("id") String id);
	int findRoleNo(@Param("roleName") String roleName);
}
