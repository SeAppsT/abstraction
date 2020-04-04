package com.example.beck.dto;

import com.example.beck.annotations.Dto;

import javax.validation.constraints.NotNull;

@Dto
public class ComponentPositionDto {

    @NotNull
    public int x;

    @NotNull
    public int y;

    @NotNull
    public Long parent_id;

    public ComponentPositionDto(){}

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }
}