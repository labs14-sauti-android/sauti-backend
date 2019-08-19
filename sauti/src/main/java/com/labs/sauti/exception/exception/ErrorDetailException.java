package com.labs.sauti.exception.exception;

import com.labs.sauti.exception.handler.ErrorDetail;

public class ErrorDetailException extends RuntimeException {

    private ErrorDetail errorDetail;

    public ErrorDetailException(ErrorDetail errorDetail) {
        this.errorDetail = errorDetail;
    }

    public ErrorDetail getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(ErrorDetail errorDetail) {
        this.errorDetail = errorDetail;
    }

    public int getStatusCode() {
        return errorDetail.getStatusCode();
    }
}
