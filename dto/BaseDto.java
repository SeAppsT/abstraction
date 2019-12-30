package com.example.beck.dto;

import com.example.beck.domain.BaseEntity;

abstract class BaseDto {

    protected String name;
    protected String description;

    public abstract BaseEntity cast(BaseEntity entity);

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}