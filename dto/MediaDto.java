package com.example.beck.dto;

import com.example.beck.annotations.Dto;
import com.example.beck.domain.BaseEntity;
import com.example.beck.domain.Media;
import com.example.beck.dto.validation.Add;

import javax.validation.constraints.NotNull;

@Dto
public class MediaDto extends BaseDto {

    @NotNull(groups = Add.class)
    public Long component_id;

    @NotNull(groups = Add.class)
    public String type;

    @Override
    public Media cast(BaseEntity entity) {
        Media file = (Media) entity;
        file.setType(this.type);
        file.setDescription(this.description);
        file.setName(this.name);
        return file;
    }

    public void setComponent_id(Long component_id) {
        this.component_id = component_id;
    }

    public void setType(String type) {
        this.type = type;
    }
}