package com.zoho.client.api.service.auth;


public interface ZohoAuthService {

    String generateAuthorizationCode();
    Object generateAccessToken(String authorizationCode);

    Object getAccessTokenFromRefreshToken(String refreshToken);
    Object refreshRevokeToken(String refreshToken);

}

