package com.zoho.client.api.dto.contact;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ContactPerson{
    public String salutation;
    @Size(max = 100, message = "First name length must be at most 200")
    public String first_name;
    @Size(max = 100, message = "First name length must be at most 200")
    public String last_name;
    public String email;
    public String phone;
    public String mobile;
    public String designation;
    public String department;
    public String skype;
    public Boolean is_primary_contact;
    public Boolean enable_portal;
}
