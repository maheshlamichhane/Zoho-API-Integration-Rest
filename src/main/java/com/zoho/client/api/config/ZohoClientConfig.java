package com.zoho.client.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ZohoClientConfig {

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public ObjectMapper objectMapper(){return new ObjectMapper();}

}
