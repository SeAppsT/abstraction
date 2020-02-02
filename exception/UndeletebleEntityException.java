package com.example.beck.exception;

public class UndeletebleEntityException extends BaseException {
    @Override
    public String getMessage() {
        return "Can't delete this entity, check relations";
    }
}