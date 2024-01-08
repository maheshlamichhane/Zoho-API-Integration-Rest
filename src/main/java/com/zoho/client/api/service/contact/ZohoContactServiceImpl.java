package com.zoho.client.api.service.contact;

import com.zoho.client.api.dto.contact.CreateContactRequest;
import com.zoho.client.api.dto.contact.EmailStatement;
import com.zoho.client.api.dto.contact.EnablePortalAccessRequest;
import com.zoho.client.api.exception.ZohoException;
import com.zoho.client.api.utility.RestTemplateHandler;
import com.zoho.client.api.utility.ZohoUtilityProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Service
public class ZohoContactServiceImpl implements ZohoContactService {

    @Value("${zoho.resource-server.base-url}")
    private String resourceServerBaseUrl;

    private final RestTemplateHandler restTemplateHandler;


    public ZohoContactServiceImpl(RestTemplateHandler restTemplateHandler) {
        this.restTemplateHandler = restTemplateHandler;
    }

    @Override
    public Object createContact(CreateContactRequest createContactRequest, String accessToken,String organizationId) {

        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("organization_id",organizationId);
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl+"/contacts",queryParams);
        String jsonObj = ZohoUtilityProvider.convertToJsonWithNonNullFields(createContactRequest);
        HttpEntity<Object> requestEntity = new HttpEntity<>(jsonObj,headers);
        ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(url,HttpMethod.POST,requestEntity);
        return responseEntity.getBody();
    }

    @Override
    public Object listContact(HttpServletRequest request) {
        validateListContactInput(request);
        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(request.getHeader("Authorization").substring(16));
        Map<String,String> queryParams = new HashMap<>();
        for(String key: request.getParameterMap().keySet()){
            queryParams.put(key,request.getParameter(key));
        }
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl+"/contacts",queryParams);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(url,HttpMethod.GET,requestEntity);
        return responseEntity.getBody();
    }

    @Override
    public Object updateContact(CreateContactRequest createContactRequest, String accessToken, String organizationId, String contactId) {

        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("organization_id",organizationId);
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl+"/contacts/"+contactId,queryParams);
        String jsonObj = ZohoUtilityProvider.convertToJsonWithNonNullFields(createContactRequest);
        HttpEntity<Object> requestEntity = new HttpEntity<>(jsonObj,headers);
        ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(url,HttpMethod.PUT,requestEntity);
        return responseEntity.getBody();
    }

    @Override
    public Object getContact(String accessToken, String organizationId, String contact_id) {

        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("organization_id",organizationId);
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl+"/contacts/"+contact_id,queryParams);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(url,HttpMethod.GET,requestEntity);
        return responseEntity.getBody();

    }

    @Override
    public Object deleteContact(String accessToken, String organizationId, String contact_id) {
        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("organization_id",organizationId);
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl+"/contacts/"+contact_id,queryParams);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(url,HttpMethod.DELETE,requestEntity);
        return responseEntity.getBody();
    }

    @Override
    public Object markAsActive(String accessToken, String organizationId, String contact_id) {
        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("organization_id",organizationId);
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl+"/contacts/"+contact_id+"/active",queryParams);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(url,HttpMethod.POST,requestEntity);
        return responseEntity.getBody();
    }

    @Override
    public Object markAsInActive(String accessToken, String organizationId, String contact_id) {
        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("organization_id",organizationId);
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl+"/contacts/"+contact_id+"/inactive",queryParams);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(url,HttpMethod.POST,requestEntity);
        return responseEntity.getBody();
    }

    @Override
    public Object enablePortalAccess(String accessToken, String organizationId, String contact_id, EnablePortalAccessRequest enablePortalAccessRequest) {
        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("organization_id",organizationId);
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl+"/contacts/"+contact_id+"/portal/enable",queryParams);
        HttpEntity<Object> requestEntity = new HttpEntity<>(enablePortalAccessRequest,headers);
        ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(url,HttpMethod.POST,requestEntity);
        return responseEntity.getBody();
    }

    @Override
    public Object enablePaymentReminders(String accessToken, String organizationId, String contact_id) {
        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("organization_id",organizationId);
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl+"/contacts/"+contact_id+"/paymentreminder/enable",queryParams);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(url,HttpMethod.POST,requestEntity);
        return responseEntity.getBody();
    }

    @Override
    public Object disablePaymentReminders(String accessToken, String organizationId, String contact_id) {
        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("organization_id",organizationId);
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl+"/contacts/"+contact_id+"/paymentreminder/disable",queryParams);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(url,HttpMethod.POST,requestEntity);
        return responseEntity.getBody();
    }

    @Override
    public Object emailStatement(String accessToken, String organizationId, String contact_id, EmailStatement emailStatement) {
        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("organization_id",organizationId);
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl+"/contacts/"+contact_id+"/statements/email",queryParams);
        String jsonObj = ZohoUtilityProvider.convertToJsonWithNonNullFields(emailStatement);
        HttpEntity<Object> requestEntity = new HttpEntity<>(jsonObj,headers);
        ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(url,HttpMethod.POST,requestEntity);
        return responseEntity.getBody();
    }

    @Override
    public Object getStatementMailContent(String accessToken, String organizationId, String contact_id) {
        HttpHeaders headers = ZohoUtilityProvider.getHttpHeaders(accessToken);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("organization_id",organizationId);
        String url = ZohoUtilityProvider.buildUrlWithQueryParams(resourceServerBaseUrl+"/contacts/"+contact_id+"/statements/email",queryParams);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplateHandler.performHttpRequest(url,HttpMethod.GET,requestEntity);
        return responseEntity.getBody();
    }


    // Validating request data
    private void validateListContactInput(HttpServletRequest request){
        for (String paramName : request.getParameterMap().keySet()) {
            String value = request.getParameter(paramName);
            if (value != null && value.length() > 100) {
                throw new ZohoException("Maximum length exceeded for "+paramName,HttpStatus.BAD_REQUEST);
            }
        }
    }
}
