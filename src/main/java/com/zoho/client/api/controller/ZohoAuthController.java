package com.zoho.client.api.controller;

import org.springframework.web.bind.annotation.*;

import com.zoho.client.api.service.auth.ZohoAuthService;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class ZohoAuthController {

    private final ZohoAuthService zohoAuthService;

    public ZohoAuthController(ZohoAuthService zohoAuthService) {
        this.zohoAuthService = zohoAuthService;
    }

    @GetMapping("/login")
    public RedirectView login() {
        String authorizationUrl = zohoAuthService.generateAuthorizationCode();
        return new RedirectView(authorizationUrl);
    }

    @PostMapping("/token")
    public Object getToken(@RequestParam("authorizationCode") String authorizationCode){
        return zohoAuthService.generateAccessToken(authorizationCode);
    }

    @PostMapping("/refreshToken")
    public Object getAccessTokenFromRefreshToken(@RequestParam("refresh_token") String refreshToken){
        return zohoAuthService.getAccessTokenFromRefreshToken(refreshToken);
    }

    @PostMapping("/oauth/v2/token/revoke")
    public Object refreshRevokeToken(@RequestParam("token") String refreshToken){
        return zohoAuthService.refreshRevokeToken(refreshToken);
    }



}
