package com.yg.portfolio.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
	// 구글로부터 받은 userRequest 데이터에 대한 후처리 되는 함수
	// userRequest.getClientRegistration() = clientId, clienSecret, scope, redirectUrl 등 정보
	// userRequest.getAccessToken() = 엑세스토큰 값
	// super.loadUser(userRequest).getAttributes() = sub(구글로그인 primary key), name, given_name, email 등의 정보
	// id = 구글 sub 사용 ex) 102359424997605896130 -> google_102359424997605896130
	// password = 암호화(killeve)로 사용 : db에 저장되있는 pw로 로그인하는게 아니기 때문에 상관없음
	// email = 구글 email
	// role = "ROLE_USER"로 사용
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration : "+userRequest.getClientRegistration()); // registrationId 로 어떤 Oauth로 로그인했는지 확인가능
		System.out.println("getAccessToken : "+userRequest.getAccessToken().getTokenValue());
		// 구글로그인버튼 클릭 -> 구글로그인창 -> 로그인 -> code리턴(OauthCline라이브러리가 받음) -> AccessToken 요청 : 여기까지가 userRequest정보
		// userRequest정보를 loadUser함수에 사용하여 회원프로필 정보를 받을 수 있음 
		System.out.println("getAttributes : "+super.loadUser(userRequest).getAttributes()); //구글 아이디 정보
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		return super.loadUser(userRequest);
	}
}
