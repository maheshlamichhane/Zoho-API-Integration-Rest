package com.zoho.client.api.utility;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateHandler {

    private final RestTemplate restTemplate;

    public RestTemplateHandler(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Object> performHttpRequest(String url,HttpMethod method, HttpEntity<Object> httpEntity) {
        return restTemplate.exchange(url, method, httpEntity, Object.class);
    }

}
