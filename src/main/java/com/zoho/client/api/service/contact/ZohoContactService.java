package com.zoho.client.api.service.contact;

import com.zoho.client.api.dto.contact.CreateContactRequest;
import com.zoho.client.api.dto.contact.EnablePortalAccessRequest;

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

}
