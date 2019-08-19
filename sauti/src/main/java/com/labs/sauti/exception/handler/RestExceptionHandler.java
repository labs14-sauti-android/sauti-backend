package com.labs.sauti.exception.handler;

import com.labs.sauti.exception.exception.ErrorDetailException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErrorDetailException.class)
    public ResponseEntity<ErrorDetail> handleErrorDetailException(ErrorDetailException errorDetailException, HttpServletRequest request) {
        HttpStatus status = HttpStatus.resolve(errorDetailException.getStatusCode());
        if (status == null) status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(errorDetailException.getErrorDetail(), status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(
                "Type Mismatch",
                HttpStatus.BAD_REQUEST.value(),
                0,
                "",
                System.currentTimeMillis(),
                ""
        );

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
}


