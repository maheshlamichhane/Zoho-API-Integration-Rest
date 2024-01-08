package com.zoho.client.api.controller;

import com.zoho.client.api.exception.ZohoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.zoho.client.api.dto.auth.ZohoTokenResponse;
import com.zoho.client.api.service.auth.ZohoAuthService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/zoho")
public class ZohoAuthController {

    @Value("${zoho.oauth-server.scope}")
    private String scope;

    @Value("${zoho.oauth-server.base-url}")
    private String oauthServerBaseUrl;

    @Value("${zoho.oauth-server.redirect-uri}")
    private String redirectUri;

    @Value("${zoho.oauth-server.client-id}")
    private String clientId;

    private final ZohoAuthService zohoAuthService;

    public ZohoAuthController(ZohoAuthService zohoAuthService) {
        this.zohoAuthService = zohoAuthService;
    }

    @GetMapping("/login")
    public void redirectToZoho(HttpServletResponse response) {
        String zohoUrl = oauthServerBaseUrl+"/v2/auth"+
                "?scope="+scope+
                "&client_id="+clientId+
                "&state=testing"+
                "&response_type=code" +
                "&redirect_uri="+redirectUri+
                "&access_type=offline";
        try{
            response.sendRedirect(zohoUrl);
        }
        catch(IOException e){
            throw new ZohoException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/token")
    public ZohoTokenResponse getToken(@RequestParam("authorizationCode") String authorizationCode){
        return zohoAuthService.generateAccessToken(authorizationCode);
    }

}
