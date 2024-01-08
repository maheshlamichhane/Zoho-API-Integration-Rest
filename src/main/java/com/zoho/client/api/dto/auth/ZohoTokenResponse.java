package com.zoho.client.api.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZohoTokenResponse {
    private String access_token;
    private String scope;
    private String api_domain;
    private String token_type;
    private Long expires_in;

}