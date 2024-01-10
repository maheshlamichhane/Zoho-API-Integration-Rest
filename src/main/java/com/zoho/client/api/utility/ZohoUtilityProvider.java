package com.zoho.client.api.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoho.client.api.exception.ZohoError;
import com.zoho.client.api.exception.ZohoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;


public class ZohoUtilityProvider {

	public static HttpHeaders getHttpHeaders(String accessToken){
		HttpHeaders headers = new HttpHeaders();
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

	public static String convertToJsonWithNonNullFields(Object request) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		try {
			return objectMapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			throw new ZohoException(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		}
	}

	public static String convertMultipartToBinary(MultipartFile attachments){
		String encodedFile = "";
		if (attachments != null && !attachments.isEmpty()) {
			try {
				byte[] fileBytes = attachments.getBytes();
				encodedFile = Base64.getEncoder().encodeToString(fileBytes);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return encodedFile;
	}

	public static ZohoError convertErrorResponseToObject(String jsonString){
		ObjectMapper objectMapper = new ObjectMapper();
		ZohoError zohoError = null;
		try{
			zohoError = objectMapper.readValue(jsonString, ZohoError.class);
		}
		catch (Exception ee){
			ee.printStackTrace();
		}
		return zohoError;
	}
}
