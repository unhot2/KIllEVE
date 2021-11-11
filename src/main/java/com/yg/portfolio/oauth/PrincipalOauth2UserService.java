package com.yg.portfolio.oauth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.yg.portfolio.model.PrincipalDetails;
import com.yg.portfolio.model.User;
import com.yg.portfolio.oauth.provider.FacebookUserInfo;
import com.yg.portfolio.oauth.provider.GoogleUserInfo;
import com.yg.portfolio.oauth.provider.NaverUserInfo;
import com.yg.portfolio.oauth.provider.OAuth2UserInfo;
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
//		System.out.println("getClientRegistration : " + userRequest.getClientRegistration()); // registrationId 로 어떤
//		System.out.println("getAccessToken : " + userRequest.getAccessToken().getTokenValue());
//		System.out.println("getAttributes : " + super.loadUser(userRequest).getAttributes()); // 구글 아이디 정보
		OAuth2User oauth2User = super.loadUser(userRequest);

		OAuth2UserInfo oAuth2UserInfo = null;
		if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
		} else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			oAuth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());

		} else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));

		} else {
			System.out.println("구글, 페이스북만 지원합니다.");
		}
		// 구글로그인버튼 클릭 -> 구글로그인창 -> 로그인 -> code리턴(OauthCline라이브러리가 받음) -> AccessToken 요청
		// : 여기까지가 userRequest정보
		// userRequest정보를 loadUser함수에 사용하여 회원프로필 정보를 받을 수 있음
		User userEntity = userRepository.findByUserId(oAuth2UserInfo.getUserId()); // userId이 있는지 찾음
		if (userEntity == null) {
			userEntity = new User();
			userEntity.setUserId(oAuth2UserInfo.getUserId());
			userEntity.setUserName(oAuth2UserInfo.getName());
			userEntity.setPassword(bCryptPasswordEncoder.encode("killeve"));
			userEntity.setEmail(oAuth2UserInfo.getEmail());
			userEntity.setRole("ROLE_USER");
			userEntity.setEmailReceiveYn("Y"); // 이메일 수신여부 기본값 Y로 설정
			userEntity.setSmsReceiveYn("Y"); // 문자 수신여부 기본값 Y로 설정
			userEntity.setProvider(oAuth2UserInfo.getProvider());
			userEntity.setProviderId(oAuth2UserInfo.getProviderId());
//			System.out.println("userEntity값 : ★★★" + userEntity);
			userRepository.oAuthJoin(userEntity);
		}
		return new PrincipalDetails(userEntity, oauth2User.getAttributes());
	}
}
