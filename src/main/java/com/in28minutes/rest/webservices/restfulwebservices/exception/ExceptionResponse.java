package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {

    private final String message;
    private final Date timeStamp;
    private final String details;

    public ExceptionResponse(String message, Date timeStamp, String details) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "message='" + message + '\'' +
                ", timeStamp=" + timeStamp +
                ", details='" + details + '\'' +
                '}';
    }
}
