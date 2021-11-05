package com.yg.portfolio.dto;

import lombok.ToString;

@ToString
public class UserVO {

	private int userNo; //회원 pk
	private String id; //회원 아이디
	private String password;// 비밀번호
	private String name; // 회원 이름
	private String roleName; //권한 이름
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
