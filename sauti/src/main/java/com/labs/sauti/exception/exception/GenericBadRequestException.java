package com.labs.sauti.exception.exception;

import org.springframework.http.HttpStatus;

public class GenericBadRequestException extends GenericException {

    public GenericBadRequestException(String title, int errorCode, String detail) {
        super(title, HttpStatus.BAD_REQUEST.value(), errorCode, detail);
    }
}
