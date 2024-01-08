package com.zoho.client.api.dto.contact;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AdditionalAddress{


    public String attention;
    @Size(max=500,message = "Address can have upto 500 characters")
    public String address;
    public String street2;
    public String city;
    public String state;
    public String zip;
    public String country;
    public String fax;
    public String phone;

    @NotNull(message = "This address_id is required")
    private String address_id;
}
