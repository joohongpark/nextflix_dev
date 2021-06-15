package com.movie.login.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

@Service
public class NaverLoginBO {
	private final static String CLIENT_ID = "58AZEJf7SLoty_0Uzp5s";
	private final static String CLIENT_SECRET = "lKrJn2alDU";
	private final static String REDIRECT_URL = "http://192.168.10.22:8080/movie/callback";
	private final static String SESSION_STATE = "oauth_state";
	/*프로필 조회 API URL*/
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
	
	/*네이버 아이디로 인증 URL생성 */
	public String getAuthorizationUrl(HttpSession session) {
		
		/*세션 유효성 검증을 위하여 난수를 생성*/
		String state = generateRandomString();
		/*생성한 난수 값을 session에 저장 */
		setSession(session, state);

		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URL)
				.state(state)
				.build(NaverLoginApi.instance());

		return oauthService.getAuthorizationUrl();
	}

	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException {
		String sessionState = getSession(session);
		if (StringUtils.equals(sessionState, state)) {
			
			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(CLIENT_ID)
					.apiSecret(CLIENT_SECRET)
					.callback(REDIRECT_URL)
					.state(state)
					.build(NaverLoginApi.instance());

			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}

	public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URL)
				.build(NaverLoginApi.instance());

		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		return response.getBody();
	}

	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}

	private void setSession(HttpSession session, String state) {
		session.setAttribute(SESSION_STATE, state);

	}

	private String getSession(HttpSession session) {
		Object attribute = session.getAttribute(SESSION_STATE);
		return attribute.toString();
	}
	
}
