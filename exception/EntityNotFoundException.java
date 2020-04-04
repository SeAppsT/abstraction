package com.example.beck.exception;

public class EntityNotFoundException extends BaseException{
    @Override
    public String getMessage() {
        return "Entity not found, try to send other id";
    }
}