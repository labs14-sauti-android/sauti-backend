package com.labs.sauti.exception.exception;

import com.labs.sauti.exception.handler.ErrorDetail;

public class GenericException extends ErrorDetailException {

    public GenericException(String title, int statusCode, int errorCode, String detail) {
        super(new ErrorDetail(
                title,
                statusCode,
                errorCode,
                detail,
                System.currentTimeMillis(),
                ""
        ));
    }
}
