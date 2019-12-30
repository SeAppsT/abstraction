package com.example.beck.exception;

public class InvalidPropertyException extends BaseException {
    @Override
    public String getMessage() {
        return "One of your property is invalid";
    }
}