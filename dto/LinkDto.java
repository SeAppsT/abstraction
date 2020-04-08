package com.example.beck.dto;

import com.example.beck.annotations.Dto;
import com.example.beck.domain.BaseEntity;
import com.example.beck.domain.Link;
import com.example.beck.dto.validation.Add;

import javax.validation.constraints.NotNull;

@Dto
public class LinkDto extends BaseDto {

    @NotNull(groups = Add.class)
    public String link;

    @NotNull(groups = Add.class)
    public String type;

    @NotNull(groups = Add.class)
    public Long component_id;

    @Override
    public Link cast(BaseEntity entity) {
        Link link = (Link) entity;
        link.setDescription(this.description);
        link.setLink(this.link);
        link.setType(this.type);
        return null;
    }


    public void setLink(String link) {
        this.link = link;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setComponent_id(Long component_id) {
        this.component_id = component_id;
    }
}