package com.zoho.client.api.service.contact;

import com.zoho.client.api.dto.contact.BillingAddress;
import com.zoho.client.api.dto.contact.CreateContactRequest;
import com.zoho.client.api.dto.contact.EmailStatement;
import com.zoho.client.api.dto.contact.EnablePortalAccessRequest;
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

    public Object addAdditionalAddress(String accessToken, String organizationId, String contact_id, BillingAddress billingAddress);

    public Object getContactAddress(String accessToken, String organizationId, String contact_id);
}
