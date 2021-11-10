package com.yg.portfolio.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
	private int id; //회원 아이디
	private String userId;
	private String userName; // 회원 이름
	private String password;// 비밀번호
	private String email; // 이메일
	private String phone;
	private String gender;
	private String emailReceiveYn;
	private String smsReceiveYn;
	private String zipCode;
	private String address;
	private String detailAddress;
	private String role; //권한 이름
	private String provider;
	private String providerId;
	
	@Builder
	public User(String userId, String userName, String password, String email, String phone, String gender,
			String emailReceiveYn, String smsReceiveYn, String zipCode, String address, String detailAddress,
			String role, String provider, String providerId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.emailReceiveYn = emailReceiveYn;
		this.smsReceiveYn = smsReceiveYn;
		this.zipCode = zipCode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
	}
	
}
