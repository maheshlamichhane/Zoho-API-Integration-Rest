package com.zoho.client.api.service.auth;

import com.zoho.client.api.utility.RestTemplateHandler;
import com.zoho.client.api.utility.ZohoUtilityProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ZohoAuthServiceImpl implements ZohoAuthService {

	@Value("${zoho.oauth-server.client-id}")
	private String clientId;

	@Value("${zoho.oauth-server.client-secret}")
	private String clientSecret;

	@Value("${zoho.oauth-server.redirect-uri}")
	private String redirectUri;


	@Value("${zoho.oauth-server.scope}")
	private String scope;

	@Value("${zoho.oauth-server.state}")
	private String state;

	@Value("${zoho.oauth-server.base-url}")
	private String authServerBaseUrl;

	@Autowired
	private RestTemplate restTemplate;


	private final RestTemplateHandler restTemplateHandler;

	public ZohoAuthServiceImpl(RestTemplateHandler restTemplateHandler) {
		this.restTemplateHandler = restTemplateHandler;
	}

	@Override
	public String generateAuthorizationCode() {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("scope",scope);
		queryParams.put("client_id", clientId);
		queryParams.put("state", state);
		queryParams.put("response_type", "code");
		queryParams.put("redirect_uri", redirectUri);
		queryParams.put("access_type", "offline");
		queryParams.put("prompt", "consent");
		return ZohoUtilityProvider.buildUrlWithQueryParams(authServerBaseUrl+"/v2/auth",queryParams);
	}

	@Override
	public Object generateAccessToken(String authorizationCode) {

		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("code",authorizationCode);
		queryParams.add("client_id", clientId);
		queryParams.add("client_secret", clientSecret);
		queryParams.add("redirect_uri", redirectUri);
		queryParams.add("grant_type", "authorization_code");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<Object> requestEntity = new HttpEntity<>(queryParams, headers);
		ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(authServerBaseUrl+"/v2/token", HttpMethod.POST,
				requestEntity);
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity.getBody();
		} else {
			return null;
		}
	}

	@Override
	public Object getAccessTokenFromRefreshToken(String refreshToken) {

		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("refresh_token",refreshToken);
		queryParams.add("client_id", clientId);
		queryParams.add("client_secret", clientSecret);
		queryParams.add("redirect_uri", redirectUri);
		queryParams.add("grant_type", "refresh_token");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<Object> requestEntity = new HttpEntity<>(queryParams, headers);
		ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(authServerBaseUrl+"/v2/token", HttpMethod.POST,
				requestEntity);
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity.getBody();
		} else {
			return null;
		}
	}

	@Override
	public Object refreshRevokeToken(String refreshToken) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("token", refreshToken);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<Object> requestEntity = new HttpEntity<>(formData, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(
				authServerBaseUrl + "/v2/token/revoke",
				HttpMethod.POST,
				requestEntity,String.class);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity.getBody();
		} else {
			return null;
		}
	}
}
