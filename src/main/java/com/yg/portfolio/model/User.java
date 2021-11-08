package com.yg.portfolio.model;

import lombok.Data;

@Data
public class User {
	private int id; //회원 아이디
	private String username; // 회원 이름
	private String password;// 비밀번호
	private String email; // 이메일
	private String role; //권한 이름
	
}
