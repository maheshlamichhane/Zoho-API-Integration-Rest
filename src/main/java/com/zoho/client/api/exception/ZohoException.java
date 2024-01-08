package com.zoho.client.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ZohoException extends RuntimeException {
    private final HttpStatus status;
    public ZohoException(
            String message,
            HttpStatus status) {
        super(message);
        this.status = status;
    }

}
