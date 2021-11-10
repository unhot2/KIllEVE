package com.yg.portfolio.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.yg.portfolio.model.PrincipalDetails;
import com.yg.portfolio.model.User;
import com.yg.portfolio.repository.UserRepository;


@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;

	// 구글로부터 받은 userRequest 데이터에 대한 후처리 되는 함수
	// userRequest.getClientRegistration() = clientId, clienSecret, scope,
	// redirectUrl 등 정보
	// userRequest.getAccessToken() = 엑세스토큰 값
	// super.loadUser(userRequest).getAttributes() = sub(구글로그인 primary key), name,
	// given_name, email 등의 정보
	// id = 구글 sub 사용 ex) 102359424997605896130 -> google_102359424997605896130
	// password = 암호화(killeve)로 사용 : db에 저장되있는 pw로 로그인하는게 아니기 때문에 상관없음
	// email = 구글 email
	// role = "ROLE_USER"로 사용

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration : " + userRequest.getClientRegistration()); // registrationId 로 어떤 Oauth로 로그인했는지 확인가능
		System.out.println("getAccessToken : " + userRequest.getAccessToken().getTokenValue());

		OAuth2User oauth2User = super.loadUser(userRequest);
		// 구글로그인버튼 클릭 -> 구글로그인창 -> 로그인 -> code리턴(OauthCline라이브러리가 받음) -> AccessToken 요청
		// : 여기까지가 userRequest정보
		// userRequest정보를 loadUser함수에 사용하여 회원프로필 정보를 받을 수 있음
		System.out.println("getAttributes : " + super.loadUser(userRequest).getAttributes()); // 구글 아이디 정보

		String provider = userRequest.getClientRegistration().getClientName(); // google
		String providerId = oauth2User.getAttribute("sub"); // 102359424997605896130
		String userId = provider + "_" + providerId; // google_102359424997605896130
		String userName = oauth2User.getAttribute("name");
		String password = bCryptPasswordEncoder.encode("killeve"); // 의미없지만 명목상 생성
		String email = oauth2User.getAttribute("email");
		String role = "ROLE_USER";
		
		
		User userEntity = userRepository.findByUserId(userId); // userId이 있는지 찾음
		if (userEntity == null) {
			userEntity = User.builder()
			.userId(userId)
			.userName(userName)
			.password(password)
			.email(email)
			.role(role)
			.build();
			System.out.println("★★★★★★★★ userEntity 값 : "+userEntity);
			userRepository.join(userEntity);
		} 
		return new PrincipalDetails(userEntity,oauth2User.getAttributes());
	}
}
