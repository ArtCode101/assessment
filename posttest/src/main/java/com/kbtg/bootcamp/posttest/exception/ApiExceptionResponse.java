package com.kbtg.bootcamp.posttest.exception;

import java.time.ZonedDateTime;

public class ApiExceptionResponse {

    private String message;

    private ZonedDateTime dateTime;

    public ApiExceptionResponse(String message, ZonedDateTime dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
