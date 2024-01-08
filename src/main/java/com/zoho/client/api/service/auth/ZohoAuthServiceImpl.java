package com.zoho.client.api.service.auth;

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

import com.zoho.client.api.dto.auth.ZohoTokenResponse;

@Service
public class ZohoAuthServiceImpl implements ZohoAuthService {

	@Value("${zoho.oauth-server.client-id}")
	private String clientId;

	@Value("${zoho.oauth-server.client-secret}")
	private String clientSecret;

	@Value("${zoho.oauth-server.redirect-uri}")
	private String redirectUri;


	@Value("${zoho.oauth-server.base-url}")
	private String baseUrl;

	private final RestTemplate restTemplate;

	public ZohoAuthServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public ZohoTokenResponse generateAccessToken(String authorizationCode) {

		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("code",authorizationCode);
		requestBody.add("client_id", clientId);
		requestBody.add("client_secret", clientSecret);
		requestBody.add("redirect_uri", redirectUri);
		requestBody.add("grant_type", "authorization_code");


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<ZohoTokenResponse> responseEntity = restTemplate.exchange(baseUrl+"/v2/token", HttpMethod.POST,
				requestEntity, ZohoTokenResponse.class);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity.getBody();
		} else {
			return null;
		}
	}

}
