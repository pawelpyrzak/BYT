package com.example.hollidayCottages.Exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionWithMessage extends Throwable {
    public ExceptionWithMessage(String message) {
        super(message);
    }
}

