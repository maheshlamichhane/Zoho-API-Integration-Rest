package com.zoho.client.api.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoho.client.api.dto.contact.CreateContactRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;


public class ZohoUtilityProvider {

	public static HttpHeaders getHttpHeaders(String accessToken){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Authorization", "Zoho-oauthtoken " + accessToken);
		return headers;
	}

	public static String buildUrlWithQueryParams(String baseUrl, Map<String, String> queryParams) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
		queryParams.forEach((key,value)-> {
			if(value != null){
				builder.queryParam(key,value);
			}
		});
		return builder.build(false).toUriString();
	}

	public static String convertToJsonWithNonNullFields(CreateContactRequest request) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		try {
			return objectMapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
