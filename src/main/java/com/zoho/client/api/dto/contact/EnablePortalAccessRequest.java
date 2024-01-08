package com.zoho.client.api.dto.contact;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class EnablePortalAccessRequest {
    private ArrayList<EnablePortalIdDTO> contact_persons;
}
