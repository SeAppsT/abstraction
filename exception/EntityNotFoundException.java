package com.example.beck.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntityNotFoundException extends BaseException{
    @Override
    public String getMessage() {
        return "Entity not found, try to send other id";
    }
}