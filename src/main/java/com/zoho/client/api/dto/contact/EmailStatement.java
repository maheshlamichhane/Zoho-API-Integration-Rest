package com.zoho.client.api.dto.contact;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Getter
@Setter
public class EmailStatement {

    private Boolean send_from_org_email_id;
    @NotNull(message = "to_mail_ids field can't be null")
    private ArrayList<String> to_mail_ids;
    private ArrayList<String> cc_mail_ids;
    @NotNull(message = "subject field can't be null")
    @Size(max = 1000, message = "Subject length must be at most 1000")
    private String subject;
    @NotNull(message = "body field can't be null")
    @Size(max = 5000, message = "Body length must be at most 5000")
    private String body;


}
