package com.zoho.client.api.service.auth;

import com.zoho.client.api.dto.auth.ZohoTokenResponse;

public interface ZohoAuthService {
    ZohoTokenResponse generateAccessToken(String authorizationCode);
}

