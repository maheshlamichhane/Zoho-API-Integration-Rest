package com.zoho.client.api.utility;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class RestTemplateHandler {

    @Value("${zoho.resource-server.base-url}")
    private String resourceServerBaseUrl;

    @Autowired
    private ObjectMapper objectMapper;

    private final RestTemplate restTemplate;

    public RestTemplateHandler(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Object> performHttpRequest(String url,HttpMethod method, HttpEntity<Object> httpEntity) {
        return restTemplate.exchange(url, method, httpEntity, Object.class);
    }


    public ResponseEntity<Object> requestForSaveOrUpdate(HttpMethod method, String accessToken, String organizationId, String endpoint, Object requestBody) {
        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("organization_id", organizationId);
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl + endpoint, queryParams);
        String jsonObj = ZohoUtilityProvider.convertToJsonWithNonNullFields(requestBody);
        HttpEntity<Object> requestEntity = new HttpEntity<>(jsonObj, headers);
        return restTemplate.exchange(url, method
                    , requestEntity, Object.class);
    }

    public ResponseEntity<Object> requestForGet(String accessToken, String organizationId, String endpoint, Map<String,String> queryParams){
        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        queryParams.put("organization_id", organizationId);

        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl + endpoint, queryParams);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object.class);
    }

    public ResponseEntity<Object> requestForDelete(String accessToken, String organizationId, String endpoint){
        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("organization_id",organizationId);
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl+endpoint,queryParams);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(url,HttpMethod.DELETE,requestEntity,Object.class);
    }

}
