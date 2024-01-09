package com.zoho.client.api.dto.contact;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ContactPerson{
    private String salutation;
    @Size(max = 100, message = "First name length must be at most 200")
    private String first_name;
    @Size(max = 100, message = "First name length must be at most 200")
    private String last_name;
    private String email;
    private String phone;
    private String mobile;
    private String designation;
    private String department;
    private String skype;
    private Boolean is_primary_contact;
    private Boolean enable_portal;
}
