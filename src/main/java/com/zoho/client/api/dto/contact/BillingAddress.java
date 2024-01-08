package com.zoho.client.api.dto.contact;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
public class BillingAddress{
    public String attention;
    @Size(max=500,message = "Address can have upto 500 characters")
    public String address;
    public String street2;
    public String state_code;
    public String city;
    public String state;
    public String zip;
    public String country;
    public String fax;
    public String phone;
}













