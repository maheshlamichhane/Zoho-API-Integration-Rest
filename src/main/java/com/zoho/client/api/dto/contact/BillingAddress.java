package com.zoho.client.api.dto.contact;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
public class BillingAddress{
    private String attention;
    @Size(max=500,message = "Address can have upto 500 characters")
    private String address;
    private String street2;
    private String state_code;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String fax;
    private String phone;
}













