package com.zoho.client.api.service.contact;

import com.zoho.client.api.dto.contact.*;
import com.zoho.client.api.exception.ZohoException;
import com.zoho.client.api.utility.RestTemplateHandler;
import com.zoho.client.api.utility.ZohoUtilityProvider;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class ZohoContactServiceImpl implements ZohoContactService {

    private final RestTemplateHandler restTemplateHandler;

    public ZohoContactServiceImpl(RestTemplateHandler restTemplateHandler) {
        this.restTemplateHandler = restTemplateHandler;
    }

    @Override
    public Object createContact(CreateContactRequest createContactRequest, String accessToken,String organizationId) {
          return restTemplateHandler.requestForSaveOrUpdate(HttpMethod.POST,accessToken,organizationId,"/contacts",createContactRequest).getBody();
    }

    @Override
    public Object listContact(HttpServletRequest request) {
        validateListContactInput(request);
        Map<String,String> queryParams = new HashMap<>();
        for(String key: request.getParameterMap().keySet()){
            queryParams.put(key,request.getParameter(key));
        }
        String accessToken = request.getHeader("Authorization").substring(16);
        String organizationId = request.getParameter("organization_id");

        return restTemplateHandler.requestForGet(accessToken,organizationId,"/contacts",queryParams).getBody();
    }

    @Override
    public Object updateContact(CreateContactRequest createContactRequest, String accessToken, String organizationId, String contactId) {
        return restTemplateHandler.requestForSaveOrUpdate(HttpMethod.PUT,accessToken,organizationId,"/contacts/"+contactId,createContactRequest).getBody();
    }

    @Override
    public Object getContact(String accessToken, String organizationId, String contact_id) {
        return restTemplateHandler.requestForGet(accessToken,organizationId,"/contacts/"+contact_id,new HashMap<>()).getBody();
    }

    @Override
    public Object deleteContact(String accessToken, String organizationId, String contact_id) {
        return restTemplateHandler.requestForDelete(accessToken,organizationId,"/contacts/"+contact_id);
    }

    @Override
    public Object markAsActive(String accessToken, String organizationId, String contact_id) {
        return restTemplateHandler.requestForSaveOrUpdate(HttpMethod.POST,accessToken,organizationId,"/contacts/"+contact_id+"/active",null);
    }

    @Override
    public Object markAsInActive(String accessToken, String organizationId, String contact_id) {
        return restTemplateHandler.requestForSaveOrUpdate(HttpMethod.POST,accessToken,organizationId,"/contacts/"+contact_id+"/inactive",null);
    }

    @Override
    public Object enablePortalAccess(String accessToken, String organizationId, String contact_id, EnablePortalAccessRequest enablePortalAccessRequest) {
        return restTemplateHandler.requestForSaveOrUpdate(HttpMethod.POST,accessToken,organizationId,"/contacts/"+contact_id+"/portal/enable",enablePortalAccessRequest);
    }

    @Override
    public Object enablePaymentReminders(String accessToken, String organizationId, String contact_id) {
        return restTemplateHandler.requestForSaveOrUpdate(HttpMethod.POST,accessToken,organizationId,"/contacts/"+contact_id+"/paymentreminder/enable",null);
    }

    @Override
    public Object disablePaymentReminders(String accessToken, String organizationId, String contact_id) {
        return restTemplateHandler.requestForSaveOrUpdate(HttpMethod.POST,accessToken,organizationId,"/contacts/"+contact_id+"/paymentreminder/disable",null);
    }

    @Override
    public Object emailStatement(String accessToken, String organizationId, String contact_id, EmailStatement emailStatement) {
        return restTemplateHandler.requestForSaveOrUpdate(HttpMethod.POST,accessToken,organizationId,"/contacts/"+contact_id+"/statements/email",emailStatement);
    }

    @Override
    public Object getStatementMailContent(String accessToken, String organizationId, String contact_id) {
        return restTemplateHandler.requestForGet(accessToken,organizationId,"/contacts/"+contact_id+"/statements/email",new HashMap<>()).getBody();
    }

    @Override
    public Object emailContact(String accessToken, String organizationId, String contact_id, String[] toMailIds, String subject, String body, MultipartFile attachments) {

        String attachedBinary = ZohoUtilityProvider.convertMultipartToBinary(attachments);
        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("to_mail_ids", Arrays.asList(toMailIds));
        requestBody.put("subject", subject);
        requestBody.put("body", body);
        requestBody.put("attachments",attachedBinary);

        return restTemplateHandler.requestForSaveOrUpdate(HttpMethod.POST,accessToken,organizationId,"/contacts/"+contact_id+"/email",requestBody);
    }

    @Override
    public Object listComments(String accessToken, String organizationId, String contact_id) {
        return restTemplateHandler.requestForGet(accessToken,organizationId,"/contacts/"+contact_id+"/comments",new HashMap<>()).getBody();
    }

    @Override
    public Object addAdditionalAddress(String accessToken, String organizationId, String contact_id, AdditionalAddress additionalAddress) {
        return restTemplateHandler.requestForSaveOrUpdate(HttpMethod.POST,accessToken,organizationId,"/contacts/"+contact_id+"/address",additionalAddress);
    }

    @Override
    public Object getContactAddress(String accessToken, String organizationId, String contact_id) {
        return restTemplateHandler.requestForGet(accessToken,organizationId,"/contacts/"+contact_id+"/address",new HashMap<>()).getBody();
    }

    @Override
    public Object editAdditionalAddress(String accessToken, String organizationId, String contactId,String addressId, AdditionalAddress additionalAddress) {
        return restTemplateHandler.requestForSaveOrUpdate(HttpMethod.PUT,accessToken,organizationId,"/contacts/"+contactId+"/address/"+addressId,additionalAddress).getBody();
    }

    @Override
    public Object deleteAdditionalAddress(String accessToken, String organizationId, String contactId, String addressId) {
        return restTemplateHandler.requestForDelete(accessToken,organizationId,"/contacts/"+contactId+"/address/"+addressId);
    }

    @Override
    public Object refunds(String accessToken, String organizationId, String contactId) {
        return restTemplateHandler.requestForGet(accessToken,organizationId,"/contacts/"+contactId+"/refunds",new HashMap<>()).getBody();
    }

    @Override
    public Object track1099(String accessToken, String organizationId, String contactId) {
        return restTemplateHandler.requestForSaveOrUpdate(HttpMethod.POST,accessToken,organizationId,"/contacts/"+contactId+"/track1099",null);
    }

    @Override
    public Object untrack1099(String accessToken, String organizationId, String contactId) {
        return restTemplateHandler.requestForSaveOrUpdate(HttpMethod.POST,accessToken,organizationId,"/contacts/"+contactId+"/untrack1099",null);
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
