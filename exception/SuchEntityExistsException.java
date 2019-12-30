package com.example.beck.exception;

import com.example.beck.domain.BaseEntity;

public class SuchEntityExistsException extends BaseException {

    private String message;

    public SuchEntityExistsException (BaseEntity entity){
        this.message = "Current entity of type " + entity.getClass() + " with id " + entity.getId() + " already exists";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}