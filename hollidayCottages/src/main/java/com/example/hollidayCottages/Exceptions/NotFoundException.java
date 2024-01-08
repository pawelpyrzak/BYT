package com.example.hollidayCottages.Exceptions;
import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND+" "+message);
    }
}
