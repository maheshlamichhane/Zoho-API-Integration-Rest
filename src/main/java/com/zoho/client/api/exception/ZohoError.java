package com.zoho.client.api.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZohoError {

    @JsonProperty("code")
    private int code;
    @JsonProperty("message")
    private String message;
}
