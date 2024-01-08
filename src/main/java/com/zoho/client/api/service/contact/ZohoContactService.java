package com.zoho.client.api.service.contact;

import com.zoho.client.api.dto.contact.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ZohoContactService {

    public Object createContact(CreateContactRequest createContactRequest, String accessToken,String organizationId);
    public Object listContact(HttpServletRequest request);

    public Object updateContact(CreateContactRequest createContactRequest,String accessToken,String organizationId,String contactId);

    public Object getContact(String accessToken,String organizationId,String contact_id);

    public Object deleteContact(String accessToken,String organizationId,String contact_id);
    public Object markAsActive(String accessToken,String organizationId,String contact_id);
    public Object markAsInActive(String accessToken,String organizationId,String contact_id);
    public Object enablePortalAccess(String accessToken, String organizationId, String contact_id, EnablePortalAccessRequest enablePortalAccessRequest);

    public Object enablePaymentReminders(String accessToken, String organizationId, String contact_id);
    public Object disablePaymentReminders(String accessToken, String organizationId, String contact_id);

    public Object emailStatement(String accessToken, String organizationId, String contact_id, EmailStatement emailStatement);

    public Object getStatementMailContent(String accessToken, String organizationId, String contact_id);

    public Object emailContact(String accessToken, String organizationId, String contact_id, String[] toMailIds, String subject, String body, MultipartFile file);

    public Object listComments(String accessToken, String organizationId, String contact_id);

    public Object addAdditionalAddress(String accessToken, String organizationId, String contact_id, AdditionalAddress additionalAddress);

    public Object getContactAddress(String accessToken, String organizationId, String contact_id);

    public Object editAdditionalAddress(String accessToken, String organizationId, String contact_id,String addressId,AdditionalAddress additionalAddress);

    public Object deleteAdditionalAddress(String accessToken, String organizationId, String contact_id,String addressId);
    public Object refunds(String accessToken, String organizationId, String contactId);

    public Object track1099(String accessToken, String organizationId, String contactId);
    public Object untrack1099(String accessToken, String organizationId, String contactId);


}
