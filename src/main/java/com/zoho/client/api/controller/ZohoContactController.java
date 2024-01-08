package com.zoho.client.api.controller;

import com.zoho.client.api.dto.contact.CreateContactRequest;
import com.zoho.client.api.dto.contact.EmailStatement;
import com.zoho.client.api.dto.contact.EnablePortalAccessRequest;
import com.zoho.client.api.service.contact.ZohoContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/books/v3")
@RestController
public class ZohoContactController {
    private final ZohoContactService zohoContactService;

    public ZohoContactController(ZohoContactService zohoContactService) {
        this.zohoContactService = zohoContactService;
    }


    @PostMapping("/contacts")
    public ResponseEntity<Object> createContact(HttpServletRequest request, @RequestParam("organization_id") String organizationId, @Valid @RequestBody  CreateContactRequest createContactRequest){
        Object object = zohoContactService.createContact(createContactRequest,request.getHeader("Authorization").substring(16),organizationId);
        return ResponseEntity.status(HttpStatus.CREATED).body(object);
    }

    @GetMapping("/contacts")
    public Object listContact(HttpServletRequest request)
    {
        return zohoContactService.listContact(request);
    }

    @PutMapping("/contacts/{contact_id}")
    public Object updateContact(HttpServletRequest request, @RequestParam("organization_id") String organizationId, @Valid @RequestBody  CreateContactRequest createContactRequest,@PathVariable("contact_id") String contactId){
        createContactRequest.setContact_id(contactId);
        return zohoContactService.updateContact(createContactRequest,request.getHeader("Authorization").substring(16),organizationId,contactId);
    }

    @GetMapping("/contacts/{contact_id}")
    public Object getContact(HttpServletRequest request,@RequestParam("organization_id") String organizationId,@PathVariable("contact_id") String contactId){
        return zohoContactService.getContact(request.getHeader("Authorization").substring(16),organizationId,contactId);
    }

    @DeleteMapping("/contacts/{contact_id}")
    public Object deleteContact(HttpServletRequest request,@RequestParam("organization_id") String organizationId,@PathVariable("contact_id") String contactId){
        return zohoContactService.deleteContact(request.getHeader("Authorization").substring(16),organizationId,contactId);
    }


    @PostMapping("/contacts/{contact_id}/active")
    public Object markAsAsActive(HttpServletRequest request,@RequestParam("organization_id") String organizationId,@PathVariable("contact_id") String contactId){
        return zohoContactService.markAsActive(request.getHeader("Authorization").substring(16),organizationId,contactId);
    }

    @PostMapping("/contacts/{contact_id}/inactive")
    public Object markAsAsInActive(HttpServletRequest request,@RequestParam("organization_id") String organizationId,@PathVariable("contact_id") String contactId){
        return zohoContactService.markAsInActive(request.getHeader("Authorization").substring(16),organizationId,contactId);
    }

    @PostMapping("/contacts/{contact_id}/portal/enable")
    public Object enablePortalAccess(HttpServletRequest request, @RequestParam("organization_id") String organizationId, @PathVariable("contact_id") String contactId, @RequestBody EnablePortalAccessRequest enablePortalAccessRequest){
        return zohoContactService.enablePortalAccess(request.getHeader("Authorization").substring(16),organizationId,contactId,enablePortalAccessRequest);
    }

    @PostMapping("/contacts/{contact_id}/paymentreminder/enable")
    public Object enablePaymentReminders(HttpServletRequest request, @RequestParam("organization_id") String organizationId, @PathVariable("contact_id") String contactId){
        return zohoContactService.enablePaymentReminders(request.getHeader("Authorization").substring(16),organizationId,contactId);
    }
    @PostMapping("/contacts/{contact_id}/paymentreminder/disable")
    public Object disablePaymentReminders(HttpServletRequest request, @RequestParam("organization_id") String organizationId, @PathVariable("contact_id") String contactId){
        return zohoContactService.disablePaymentReminders(request.getHeader("Authorization").substring(16),organizationId,contactId);
    }

    @PostMapping("/contacts/{contact_id}/statements/email")
    public Object emailStatement(HttpServletRequest request, @RequestParam("organization_id") String organizationId, @PathVariable("contact_id") String contactId, @Valid @RequestBody EmailStatement emailStatement){
        return zohoContactService.emailStatement(request.getHeader("Authorization").substring(16),organizationId,contactId,emailStatement);
    }

    @GetMapping("/contacts/{contact_id}/statements/email")
    public Object getStatementMailContent(HttpServletRequest request, @RequestParam("organization_id") String organizationId, @PathVariable("contact_id") String contactId){
        return zohoContactService.getStatementMailContent(request.getHeader("Authorization").substring(16),organizationId,contactId);
    }

    @PostMapping("/contacts/{contact_id}/email")
    public Object emailContact(HttpServletRequest request,
                               @PathVariable("contact_id") String contactId,
                               @RequestParam(name="organization_id",required = true) String organizationId,
                               @RequestParam(name="to_mail_ids",required = true) String[] toMailIds,
                               @RequestParam(name="subject",required = true) String subject,
                               @RequestParam(name="body",required = true) String body,
                               @RequestParam("file") MultipartFile attachments){
        return zohoContactService.emailContact(request.getHeader("Authorization").substring(16),organizationId,contactId,toMailIds,subject,body,attachments);
    }

    @GetMapping("/contacts/{contact_id}/comments")
    public Object listComments(HttpServletRequest request,@PathVariable("contact_id") String contactId,@RequestParam("organization_id") String organizationId){
        return zohoContactService.listComments(request.getHeader("Authorization").substring(16),organizationId,contactId);
    }
}