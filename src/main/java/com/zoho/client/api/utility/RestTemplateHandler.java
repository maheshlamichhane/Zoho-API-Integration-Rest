package com.zoho.client.api.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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
