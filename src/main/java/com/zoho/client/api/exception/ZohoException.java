package com.zoho.client.api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ZohoException extends RuntimeException {
    private final int code;
    public ZohoException(
            String message,
            int code) {
        super(message);
        this.code = code;
    }

}
