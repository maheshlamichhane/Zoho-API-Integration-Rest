package com.zoho.client.api.service.auth;

import com.zoho.client.api.dto.auth.ZohoTokenResponse;

public interface ZohoAuthService {

    String generateAuthorizationCode();
    Object generateAccessToken(String authorizationCode);

    Object getAccessTokenFromRefreshToken(String refreshToken);
    Object refreshRevokeToken(String accessToken);

}

