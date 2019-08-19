package com.labs.sauti.exception.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorDetail {
    private String title;
    private int statusCode;
    private int errorCode;
    private String detail;
    private String timestamp;
    private String developerMessage;

    public ErrorDetail() {
    }

    public ErrorDetail(String title, int statusCode, int errorCode, String detail, long timestamp, String developerMessage) {
        this.title = title;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.detail = detail;
        setTimestamp(timestamp);
        this.developerMessage = developerMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z").format(new Date(timestamp));
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}